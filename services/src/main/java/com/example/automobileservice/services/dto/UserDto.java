package com.example.automobileservice.services.dto;

import com.example.automobileservice.converter.UserRoleConverter;
import com.example.automobileservice.entity.Request;
import com.example.automobileservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UserDto {

    private Long id;

    @Pattern(regexp = "^[\sa-zA-z]{2,20}$", message = "Should contain a-z, A-Z, " +
            "or space and be not shorter than 2 chars and no longer, than 20 chars")
    private String firstName;

    @Pattern(regexp = "^[\sa-zA-z]{2,20}$", message = "Should contain a-z, A-Z, " +
            "or space and be not shorter than 2 chars and no longer, than 20 chars")
    private String lastName;

    @Pattern(regexp = "^[\sa-zA-z]{2,20}$", message = "Should contain a-z, A-Z, " +
            "or space and be not shorter than 2 chars and no longer, than 20 chars")
    private String middleName;

    @Pattern(regexp = "^.{5,20}$", message = "This field should has from 5 to 20 chars")
    @Email(message = "Should has e-mail format")
    private String email;

    @Pattern(regexp = "^\\+375 \\((25|29|33|44)\\) [0-9]{3}-[0-9]{2}-[0-9]{2}$",
            message = "Input number in +375 (XX) XXX-XX-XX format")
    private String phoneNumber;

    @Pattern(regexp = "^[a-zA-z]{5,20}$", message = "Should contain a-z, A-Z, " +
            "and be not shorter than 5 chars and no longer, than 20 chars")
    private String login;

    @Pattern(regexp = "^[0-9a-zA-z]{8,15}$", message = "Should contain 0-9, a-z or A-Z, " +
            "and be not shorter than 8 chars and no longer, than 15 chars")
    private String password;

    @Pattern(regexp = "^[0-9a-zA-z]{8,15}$", message = "Don't matches")
    private String confirmPassword;

    private UserRole role;

}
