package com.crud.demo.controllers;

import com.crud.demo.payload.ApiResponse;
import com.crud.demo.payload.StudentDto;
import com.crud.demo.service.StudentService;
import com.crud.demo.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getAllStudents")
    @ResponseBody
    public HttpEntity<?> getAllStudents(@RequestParam(value = "page", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER)
                                                Integer page,
                                        @RequestParam(value = "size", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE)
                                                Integer size) {
        ApiResponse allStudents = studentService.getAllStudents(page, size);
        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("/getStudetsById")
    @ResponseBody
    public HttpEntity<?> getStudentsById(@RequestParam Integer id) {
        ApiResponse studentById = studentService.getStudentById(id);
        return ResponseEntity.ok(studentById);
    }

    @PostMapping("/addOrEditStudent")
    public String addOrEditStudent(@RequestParam(required = false) Integer id,
                                   @RequestParam String fistname,
                                   @RequestParam String lastname,
                                   @RequestParam String phone,
                                   @RequestParam String isActive
    ) {
        StudentDto dto;
        if(id != null){
            dto = new StudentDto(fistname, lastname, phone, isActive);
        }else{
            dto = new StudentDto(id, fistname, lastname, phone, isActive);
        }
        ApiResponse addOrEdit = studentService.addOrEditStudent(dto);
        if (addOrEdit.isSuccess()) {
            return "index";
        } else {
            return "add";
        }
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public HttpEntity<?> deleteStudentById(@PathVariable Integer id) {
        ApiResponse deleteStudent = studentService.deleteStudent(id);
        return ResponseEntity.ok(deleteStudent);
    }
}

