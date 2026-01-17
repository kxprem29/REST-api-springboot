package com.kxprem.RESTAPI.controller;

import com.kxprem.RESTAPI.dto.StudentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    public StudentDTO getStudent(){
        return new StudentDTO(4L, "rahul","rahul@gmail.com");
    }
}
