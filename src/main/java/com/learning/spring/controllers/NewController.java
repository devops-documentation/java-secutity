package com.learning.spring.controllers;

import com.learning.spring.Dto.StudentsDto;
import com.learning.spring.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class NewController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<StudentsDto> getById(@PathVariable String id) {
        log.info("Inside NewController Class::Getting Student By Id {}", id);
        return new ResponseEntity<>(studentService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentsDto>> getAllStudents() {
        log.info("Inside NewController Class::Getting All Students");
       return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addStudent(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) int age,
            @RequestParam(required = false) List<String> skills,
            @RequestParam(value = "profile",required = false) MultipartFile profile
    ) throws IOException {
        StudentsDto student = new StudentsDto();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setSkills(skills);
        student.setProfile(profile.getBytes());
//        if (profile != null && !profile.isEmpty()) {
//            student.setProfile(profile.getBytes());
//        } else {
//            student.setProfile(null);
//        }
        studentService.addStudent(student);
        return new ResponseEntity<>("Student Created Successfully", HttpStatus.CREATED);
    }



    @PutMapping()
    public ResponseEntity<String> updateStudent(@RequestBody StudentsDto students) throws Exception {
        studentService.updateStudent(students);
        return new ResponseEntity<>("Student Updated Successfully",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return "Student Deleted Successfully";
    }

}
