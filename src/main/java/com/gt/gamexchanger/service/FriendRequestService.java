package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.exception.NoRequestExistException;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.RequestFriend;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.FriendRequestRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final DtoMapper<RequestFriendDto, RequestFriend> dtoMapper;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, UserRepository userRepository, DtoMapper<RequestFriendDto, RequestFriend> dtoMapper) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository=userRepository;
        this.dtoMapper = dtoMapper;
    }

   public RequestFriendDto addFriendRequest(RequestFriendDto requestFriendDto) {

        Optional<RequestFriendDto> requestFriendFinded=getAllRequest()
                .stream()
                .filter(requestFriendDto1 -> requestFriendDto1.getFromUserId().getId().equals(requestFriendDto.getFromUserId().getId())
                        && requestFriendDto1.getToUserId().getId().equals(requestFriendDto.getToUserId().getId())).findFirst();
        List<User> friends=userRepository.findById(requestFriendDto.getFromUserId().getId()).get().getFriends();
        Optional<User> userFinded =friends.stream()
                .filter(user -> user.getId().equals(requestFriendDto.getToUserId().getId())).findFirst();
        if(requestFriendFinded.isEmpty() && userFinded.isEmpty()){
            var requestFriend = dtoMapper.toDomainObject(requestFriendDto);
            friendRequestRepository.save(requestFriend);
            Optional<User> fromUser= userRepository.findById(requestFriendDto.getFromUserId().getId());
            Optional<User> toUser= userRepository.findById(requestFriendDto.getToUserId().getId());
            if(fromUser.isPresent()){
                List<RequestFriend> sendRequests =fromUser.get().getSendRequests();
                sendRequests.add(requestFriend);
                fromUser.get().setSendRequests(sendRequests);
            }
            if(toUser.isPresent()){
                List<RequestFriend> receivedRequests =toUser.get().getReceivedRequests();
                receivedRequests.add(requestFriend);
                toUser.get().setReceivedRequests(receivedRequests);
            }
            return dtoMapper.toDto(requestFriend);
        }else{
            return null;
        }
    }
    public List<RequestFriendDto> getAllRequest() {
        return friendRequestRepository.getAllRequest().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    public List<RequestFriendDto> getMySendFriendRequest(Long userId) {
        return friendRequestRepository.getRequestFriendsByFromUserId(userId).stream()
                .map(dtoMapper::toDto)
                .toList();
    }
   public List<RequestFriendDto> getReceivedFriendRequest(Long userId) {
        return friendRequestRepository.getRequestFriendsByToUserId(userId).stream()
                .map(dtoMapper::toDto)
                .toList();
    }
    public RequestFriend updateStatus(Long requestId, RequestStatus requestStatus) {
        Optional<RequestFriend> requestFriendOptional = friendRequestRepository.getRequestFriendByRequestFriendId(requestId);
        if (requestFriendOptional.isPresent()) {
            requestFriendOptional.get().setRequestStatus(requestStatus);
            friendRequestRepository.save(requestFriendOptional.get());
            if(requestStatus.equals(RequestStatus.ACCEPTED)){
                acceptFriend(requestFriendOptional.get());
            }
            if(requestStatus.equals(RequestStatus.REJECTED)){
                rejectFriend(requestFriendOptional.get());
            }
            return requestFriendOptional.get();
        }else {
            throw new NoRequestExistException();
        }
    }

    public boolean removeFriendRequestById(Long friendRequestId) {
        if (friendRequestRepository.getRequestFriendByRequestFriendId(friendRequestId).isPresent()) {
            friendRequestRepository.deleteById(friendRequestId);
            return true;
        }else{
            throw new NoRequestExistException();
        }
    }

    private void acceptFriend(RequestFriend requestFriend) {

        User firstUser = requestFriend.getFromUserId();
        User secondUser = requestFriend.getToUserId();
        firstUser.getFriends().add(secondUser);
        secondUser.getFriends().add(firstUser);
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        friendRequestRepository.deleteById(requestFriend.getRequestFriendId());
        //firstUser.getSendRequests().remove(requestFriend);
        //secondUser.getReceivedRequests().remove(requestFriend);
        //usuwam to zaprszenie z listy zaproszen przyjaciela od którego otrzymalam//
        RequestFriendDto acceptedRequest= dtoMapper.toDto(requestFriend);
        List<RequestFriendDto> requestFriendFindedList=getAllRequest()
                .stream()
                .filter(requestFriendDto1 -> requestFriendDto1.getFromUserId().getId().equals(acceptedRequest.getToUserId().getId())
                        && requestFriendDto1.getToUserId().getId().equals(acceptedRequest.getFromUserId().getId())).collect(Collectors.toList());
        for (RequestFriendDto request: requestFriendFindedList) {
            friendRequestRepository.deleteById(request.getRequestFriendId());
        }


    }

    private void rejectFriend(RequestFriend requestFriend) {
        friendRequestRepository.deleteById(requestFriend.getRequestFriendId());
        RequestFriendDto acceptedRequest= dtoMapper.toDto(requestFriend);
        List<RequestFriendDto> requestFriendFindedList=getAllRequest()
                .stream()
                .filter(requestFriendDto1 -> requestFriendDto1.getFromUserId().getId().equals(acceptedRequest.getToUserId().getId())
                        && requestFriendDto1.getToUserId().getId().equals(acceptedRequest.getFromUserId().getId())).collect(Collectors.toList());
        for (RequestFriendDto request: requestFriendFindedList) {
            friendRequestRepository.deleteById(request.getRequestFriendId());
        }
    }


}
