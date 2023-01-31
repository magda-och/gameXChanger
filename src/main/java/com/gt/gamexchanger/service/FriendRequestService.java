package com.gt.gamexchanger.service;

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

   /* public RequestFriendDto addFriendRequest(RequestFriendDto requestFriendDto) {
        var requestFreind = dtoMapper.toDomainObject(requestFriendDto);
        friendRequestRepository.save(requestFreind);
        return dtoMapper.toDto(requestFreind);
    }*/
    public List<RequestFriend> getAllRequest() {
        return friendRequestRepository.getAllRequest();
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

    /*public void updateRequest(long requestId, RequestFriendDto requestFreindDto) {
        var requestFriendOptional = getRequestById(requestId);
        if (requestFriendOptional.isPresent()) {
            var requestFreind = requestFriendOptional.get();
            updateRequestStatus(requestFreindDto, requestFreind);
        }
    }*/
   /* private RequestFriend updateRequestStatus(RequestFriendDto requestFreindDto, RequestFriend requestFriend) {
        if (requestFreindDto.getRequestStatus() != null) {
            requestFriend.setRequestStatus(requestFreindDto.getRequestStatus());
        }
        return requestFriend;
    }*/

   /* public boolean removeFriendRequestById(Long friendRequestId) {
        if (getRequestById(friendRequestId).isPresent()) {
            friendRequestRepository.removeFriendRequestById(friendRequestId);
            return true;
        }
        return false;
    }*/
   /* private Optional<RequestFriend> getRequestById(Long requestId) {
        return Optional.ofNullable(friendRequestRepository.getRequestById(requestId));
    }*/


}
