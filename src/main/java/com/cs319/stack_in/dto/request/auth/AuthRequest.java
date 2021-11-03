package com.cs319.stack_in.dto.request.auth;

import lombok.*;

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
