package com.gt.gamexchanger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "gameRequest")
public class RequestGame {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestFriendId;
    @Enumerated
    private RequestStatus requestStatus;
    @ManyToOne
    @JsonBackReference("sendRequests")
    private User fromUserId;
    @ManyToOne
    @JsonBackReference("receivedRequests")
    private User toUserId;
    private String message;

}
