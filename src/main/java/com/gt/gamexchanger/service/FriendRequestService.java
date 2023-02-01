package com.gt.gamexchanger.service;

import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.RequestFriend;
import com.gt.gamexchanger.dto.RequestFriendDto;
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
        var requestFreind = dtoMapper.toDomainObject(requestFriendDto);
        friendRequestRepository.save(requestFreind);
        Optional<User> fromUser= userRepository.findUserById(requestFriendDto.getFromUserId().getId());
        Optional<User> toUser= userRepository.findUserById(requestFriendDto.getToUserId().getId());
        if(fromUser.isPresent()){
            List<RequestFriend> sendRequests =fromUser.get().getSendRequests();
            sendRequests.add(requestFreind);
            fromUser.get().setSendRequests(sendRequests);
        }
        if(toUser.isPresent()){
            List<RequestFriend> receivedRequests =toUser.get().getReceivedRequests();
            receivedRequests.add(requestFreind);
            toUser.get().setReceivedRequests(receivedRequests);
        }
        //
        return dtoMapper.toDto(requestFreind);
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
        }
        friendRequestRepository.save(requestFriendOptional.get());
        return requestFriendOptional.get();
    }

    public boolean removeFriendRequestById(Long friendRequestId) {
        if (friendRequestRepository.getRequestFriendByRequestFriendId(friendRequestId).isPresent()) {
            friendRequestRepository.deleteById(friendRequestId);
            return true;
        }
        return false;
    }
}
