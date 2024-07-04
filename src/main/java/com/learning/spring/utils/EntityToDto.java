package com.learning.spring.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.spring.Dto.StudentsDto;
import com.learning.spring.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EntityToDto {
    @Autowired
    private ObjectMapper mapper;

    public StudentsDto getStudents(Students students){
        StudentsDto studentsDto = mapper.convertValue(students, StudentsDto.class);
        studentsDto.setPassword(null);
     return    studentsDto;
    }


}
