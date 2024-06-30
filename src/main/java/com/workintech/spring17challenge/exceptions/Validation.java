package com.workintech.spring17challenge.exceptions;

import org.springframework.http.HttpStatus;

public class Validation {

    public static void checkName(String name) {
        if( name == null || name.isEmpty() ){
            throw new ApiException("Name cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkCredit(Integer credit){
        if(credit == null || credit < 0 || credit > 4 ){
            throw new ApiException("Credit can not be null or can between [0-4].",HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkId(Integer id) {
        if( id == null || id < 0){
            throw new ApiException("Id can not be null or zero! id = "+ id, HttpStatus.BAD_REQUEST);
        }
    }
}
