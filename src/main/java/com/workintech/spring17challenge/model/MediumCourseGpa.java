package com.workintech.spring17challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class MediumCourseGpa implements CourseGpa{
    @Override
    public int getGpa() {
        return 5;
    }
}
