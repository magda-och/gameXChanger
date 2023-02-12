package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.RequestFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<RequestFriend, Long> {
    @Query("SELECT r FROM RequestFriend r")
    List<RequestFriend> getAllRequest();
    @Query("SELECT r FROM RequestFriend r where r.fromUserId.id=:id")
    List<RequestFriend> getRequestFriendsByFromUserId(@Param("id") Long id);
    @Query("SELECT r FROM RequestFriend r where r.toUserId.id=:id")
    List<RequestFriend> getRequestFriendsByToUserId(@Param("id") Long id);

    @Query("SELECT r FROM RequestFriend r where r.requestFriendId=:id")
    Optional<RequestFriend> getRequestFriendByRequestFriendId(@Param("id") Long id);
    //RequestFriendDto addFriendRequest(RequestFriendDto requestFriendDto);
}
