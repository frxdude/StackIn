package com.cs319.stack_in.dto.request.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * AuthRegisterRequest
 *
 * @author Ariunaa Gantumur
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequest {

    @NotBlank(message = "{val.not.null}")
    private String username;

    @NotBlank(message = "{val.not.null}")
    private String email;

    @NotBlank(message = "{val.not.null}")
    private String password;

    @NotBlank(message = "{val.not.null}")
    private String rePass;
}
