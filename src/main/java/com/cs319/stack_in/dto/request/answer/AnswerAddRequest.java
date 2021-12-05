package com.cs319.stack_in.dto.request.answer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnswerAddRequest {

    @NotBlank(message = "{val.not.null}")
    private String answer;

    private int votes;
}
