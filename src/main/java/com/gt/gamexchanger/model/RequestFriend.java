package com.gt.gamexchanger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gt.gamexchanger.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friendRequest")
public class RequestFriend {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestFriendId;
    @Enumerated
    private RequestStatus requestStatus;
    @ManyToOne
    @JoinColumn
    private User fromUserId;
    @ManyToOne
    @JoinColumn

    private User toUserId;
    private String message;

    public RequestFriend(RequestStatus requestStatus, User fromUserId, User toUserId, String message) {
        this.requestStatus = requestStatus;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.message=message;
    }
}
