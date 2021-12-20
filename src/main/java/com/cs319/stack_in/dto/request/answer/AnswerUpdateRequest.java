package com.cs319.stack_in.dto.request.answer;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * AnswerUpdateRequest
 *
 * @author Ariunaa Gantumur
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnswerUpdateRequest {
    @NotBlank(message = "{val.not.null}")
    private String answer;

    private int votes;
}
