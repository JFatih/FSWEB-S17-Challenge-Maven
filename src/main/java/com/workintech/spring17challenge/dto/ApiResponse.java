package com.workintech.spring17challenge.dto;

import com.workintech.spring17challenge.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private Course course;
    private Integer totalGpa;

}
