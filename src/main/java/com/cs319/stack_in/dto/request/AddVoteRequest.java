package com.cs319.stack_in.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * AddVoteRequest
 *
 * @author Ariunaa Gantumur
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddVoteRequest {
    @NotNull(message = "{val.not.null}")
    private int vote;
}
