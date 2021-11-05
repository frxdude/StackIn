package com.cs319.stack_in.dto.request.question;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAddRequest {

    @NotBlank(message = "{val.not.null}")
    private String title;

    @NotBlank(message = "{val.not.null}")
    private String description;

    private Long correctAnswerId;

    @NotBlank(message = "{val.not.null}")
    private String userId;

    @NotNull(message = "{val.not.null}")
    private int upVotes;

}
