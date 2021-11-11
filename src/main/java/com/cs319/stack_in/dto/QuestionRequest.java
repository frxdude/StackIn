package com.cs319.stack_in.dto;

import com.cs319.stack_in.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionRequest {
    private String title;
    private String description;
    private Long userId;

}
