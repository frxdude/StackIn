package com.cs319.stack_in.dto.request.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * AuthRequest
 *
 * @author Sainjargal Ishdorj
 **/

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AuthRequest {

    private String username;

    private String password;

}
