package com.learning.spring.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.spring.Dto.StudentsDto;
import com.learning.spring.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoTOEntity {

    @Autowired
    private ObjectMapper mapper;

    public Students getStudentsDto(StudentsDto dto) {
    Students students = mapper.convertValue(dto,Students.class);
    students.setPassword(PasswordEncrypt.generateStorngPasswordHash(dto.getPassword()));
        return students;
    }
}
