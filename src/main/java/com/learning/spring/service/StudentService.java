package com.learning.spring.service;

import com.learning.spring.Dto.StudentsDto;
import com.learning.spring.Exception.ResourceNotFoundException;
import com.learning.spring.entity.Students;
import com.learning.spring.repository.StudentRepository;
import com.learning.spring.utils.DtoTOEntity;
import com.learning.spring.utils.EntityToDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityToDto entityToDto;

    @Autowired
    private DtoTOEntity dtoTOEntity;

    public StudentsDto getById(String id) {
        log.info("Inside StudentService Class::Getting Student By Id {}", id);
        Students students = this.studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not Found"));
       return entityToDto.getStudents(students);
    }

    public List<StudentsDto> getAll() {
        return studentRepository.findAll().stream().map(entityToDto::getStudents).collect(Collectors.toList());
    }

    public StudentsDto addStudent(StudentsDto students) {
        return entityToDto.getStudents(studentRepository.save(dtoTOEntity.getStudentsDto(students)));
    }

    public StudentsDto updateStudent(StudentsDto student) throws Exception {
     Students existingStudent = studentRepository.findById(student.getId()).orElseThrow(()-> new IllegalArgumentException("Student not Found"));
        log.info("Inside StudentService Class::Updating Student By Id {}", student.getId());
     updateStudentIfNotNull(existingStudent::setId,student.getId());
     updateStudentIfNotNull(existingStudent::setName,student.getName());
     updateStudentIfNotNull(existingStudent::setSkills,student.getSkills());
     existingStudent.setAge(student.getAge()==0 ?existingStudent.getAge() : student.getAge());

        log.info("Inside StudentService Class::Student Updated Successfully");
        return entityToDto.getStudents(studentRepository.save(existingStudent));
    }

    private <T> void  updateStudentIfNotNull(Consumer<T> setter, T value) {
        if(value != null) {
            setter.accept(value);
        }
    }
    public void deleteStudent(String id) {
        studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not Found"));
        log.info("Inside StudentService Class::Deleting Student By Id {}", id);
        studentRepository.deleteById(id);
    }
}
