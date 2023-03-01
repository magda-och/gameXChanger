package com.gt.gamexchanger.payload.response;

import com.gt.gamexchanger.enums.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
/*    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Role roles;*/
}
