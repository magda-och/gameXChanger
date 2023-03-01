package com.gt.gamexchanger.payload.response;

import com.gt.gamexchanger.enums.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer ";
    private Long id;
    private String username;
    private String email;
    private Role role;

    public JwtResponse(String accessToken, Long id, String username, String email, Role role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
