package com.cs319.stack_in.dto.request.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * AuthRequest
 *
 * @author Sainjargal Ishdorj
 **/

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class GenerateOTPRequest {

    @NotBlank(message = "{val.not.null}")
    private String value;

}
