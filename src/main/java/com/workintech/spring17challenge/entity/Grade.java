package com.workintech.spring17challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Data
public class Grade {
    private Integer coefficient;
    private String note;
}
