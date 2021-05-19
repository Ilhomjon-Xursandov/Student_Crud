package com.crud.demo.service;

import com.crud.demo.entity.Student;
import com.crud.demo.exception.StudentException;
import com.crud.demo.payload.ApiResponse;
import com.crud.demo.payload.StudentDto;
import com.crud.demo.repository.StudentRepository;
import com.crud.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public ApiResponse getAllStudents(Integer page, Integer size){
        try {
            Page<Student> studentPage = studentRepository.findAll(CommonUtils.simplePageable(page, size));
            return new ApiResponse("Successfully", true, studentPage.getContent().stream()
                    .map(this::getStudentDtoFromStudent).collect(Collectors.toList()));
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
    public StudentDto getStudentDtoFromStudent(Student student){
        String active = Boolean.toString(student.isActive());
        return new StudentDto(
                student.getId(),
                student.getFistname(),
                student.getLastname(),
                student.getPhone(),
                active
                );
    }

    public ApiResponse getStudentById(Integer id) {
        try{
            Optional<Student> byId = studentRepository.findById(id);
            if(byId.isPresent()){
                return new ApiResponse("Success", true, getStudentDtoFromStudent(byId.get()));
            }
            return new ApiResponse("Not found ", false);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteStudent(Integer id) {
        try{
            studentRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error In Deleted", false);
        }
    }

    public  ApiResponse addOrEditStudent(StudentDto dto){
        try {

            Student student = new Student();
            if (dto.getId() != null) {
                student = studentRepository.findById(dto.getId()).orElseThrow(() -> new StudentException("Student not found"));
            }
            student.setFistname(dto.getFistname());
            student.setLastname(dto.getFistname());
            student.setPhone(dto.getPhone());
            student.setActive(Boolean.getBoolean(dto.getIsActive()));
            studentRepository.save(student);
            return new ApiResponse(dto.getId() == null ? "Added" : "Edited", true);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}
