package com.workintech.spring17challenge.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {
    private Integer status;
    private String message;
    private long timestamp;
}
