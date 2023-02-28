package com.gt.gamexchanger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String city;
    private int phoneNumber;

    public UserDto(String firstName, String lastName, String email, String password, String city, int phoneNumber) {
        this.firstName=firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
        this.city=city;
        this.phoneNumber=phoneNumber;
    }
}
