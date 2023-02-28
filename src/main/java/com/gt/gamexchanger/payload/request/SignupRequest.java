package com.gt.gamexchanger.payload.request;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    private String city;
    private int phoneNumber;

    //private Set<String> role;

}
