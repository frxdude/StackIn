package com.cs319.stack_in.dto.request.question;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * QuestionUpdateRequest
 *
 * @author Ariunaa Gantumur
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionUpdateRequest {
    @NotBlank(message = "{val.not.null}")
    private String title;

    @NotBlank(message = "{val.not.null}")
    private String description;

    @NotNull(message = "{val.not.null}")
    private Long id;

    @NotNull(message = "{val.not.null}")
    private int upVotes;
}
