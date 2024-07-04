package com.learning.spring.Exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorMessage {
    private String message;
    private int statusCode;
    private Date timestamp;
    private String description;
}
