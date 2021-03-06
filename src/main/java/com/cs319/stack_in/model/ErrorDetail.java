package com.cs319.stack_in.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ErrorDetail
 *
 * @author Sainjargal Ishdorj
 **/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetail {

    private String code, message;

}