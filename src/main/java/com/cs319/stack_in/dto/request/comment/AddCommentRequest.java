package com.cs319.stack_in.dto.request.comment;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * AddCommentRequest
 *
 * @author Ariunaa Gantumur
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {

//    @NotEmpty(message = "{val.not.empty}")
    private Long parentId;

    @NotNull(message = "{val.not.null}")
    private String comment;
}
