package com.gt.gamexchanger.service;

import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.RequestFriend;
import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final DtoMapper<RequestFriendDto, RequestFriend> dtoMapper;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, DtoMapper<RequestFriendDto, RequestFriend> dtoMapper) {
        this.friendRequestRepository = friendRequestRepository;
        this.dtoMapper = dtoMapper;
    }

   public RequestFriendDto addFriendRequest(RequestFriendDto requestFriendDto) {
        var requestFreind = dtoMapper.toDomainObject(requestFriendDto);
        friendRequestRepository.save(requestFreind);
        return dtoMapper.toDto(requestFreind);
    }
    public List<RequestFriendDto> getAllRequest() {

        return friendRequestRepository.getAllRequest().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

   /* public List<RequestFriendDto> getMySendFriendRequest(Long userId) {
        return friendRequestRepository.getMySendFriendRequest(userId).stream()
                .map(dtoMapper::toDto)
                .toList();
    }*/
   /* public List<RequestFriendDto> getReceivedFriendRequest(Long userId) {
        return friendRequestRepository.getReceivedFriendRequest(userId).stream()
                .map(dtoMapper::toDto)
                .toList();
    }*/
    public RequestFriend updateStatus(Long requestId, RequestStatus requestStatus) {
        Optional<RequestFriend> requestFriendOptional = friendRequestRepository.getRequestFriendByRequestFriendId(requestId);
        if (requestFriendOptional.isPresent()) {
            requestFriendOptional.get().setRequestStatus(requestStatus);
        }
        friendRequestRepository.save(requestFriendOptional.get());
        return requestFriendOptional.get();
    }

   /* public boolean removeFriendRequestById(Long friendRequestId) {
        if (getRequestById(friendRequestId).isPresent()) {
            friendRequestRepository.removeFriendRequestById(friendRequestId);
            return true;
        }
        return false;
    }*/
   /*
   //poprawić do updateStatus w serwisie i dodac taka metode do repository
   private Optional<RequestFriend> getRequestById(Long requestId) {
        return Optional.ofNullable(friendRequestRepository.getRequestById(requestId));
    }*/


}
