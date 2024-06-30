package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.dto.ApiResponse;
import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.exceptions.ApiErrorResponse;
import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.exceptions.Validation;
import com.workintech.spring17challenge.model.CourseGpa;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses;
    private final CourseGpa lowCourseGpa;
    private final CourseGpa mediumCourseGpa;
    private final CourseGpa highCourseGpa;


    @PostConstruct
    public void init(){
        this.courses = new ArrayList<>();
    }

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa,
                            @Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa,
                            @Qualifier("highCourseGpa") CourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }


    @GetMapping
    public List<Course> getCoursesList(){
        return courses;
    }

    @GetMapping("/{name}")
    public Course getCourseData(@PathVariable String name){
        Validation.checkName(name);
        return courses.stream().filter( (data) -> data.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow( () -> new ApiException("Course not found with given name: " + name, HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCourse(@RequestBody Course course){
        Validation.checkName(course.getName());
        Validation.checkCredit(course.getCredit());
        int totalGpa = getTotalGpa(course);
        courses.add(course);
        return new ResponseEntity<>(new ApiResponse(course,totalGpa),HttpStatus.CREATED);
    }

    private int getTotalGpa(Course course){
        int totalGpa = 0;
        if(lowCourseGpa.getGpa() == 3){
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
        } else if(mediumCourseGpa.getGpa() == 5){
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
        } else if(highCourseGpa.getGpa() == 10){
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }
        return totalGpa;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable int id, @RequestBody Course course){
        Validation.checkId(course.getId());
        Validation.checkName(course.getName());
        Validation.checkCredit(course.getCredit());
        course.setId(id);
        courses.forEach( (data) -> {
            if(data.getId().equals(id)){
                data.setName(course.getName());
                data.setCredit(course.getCredit());
                data.setGrade(course.getGrade());
            }
        });
        int totalGpa = getTotalGpa(course);
        return new ResponseEntity<>(new ApiResponse(course, totalGpa), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Course deleteCourse(@PathVariable("id") int id){
        Validation.checkId(id);
        Course course = new Course();
        Iterator<Course> iterator = courses.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId() == id){
                iterator.remove();
                course = iterator.next();
                break;
            }

        }
        return course;
    }
}
