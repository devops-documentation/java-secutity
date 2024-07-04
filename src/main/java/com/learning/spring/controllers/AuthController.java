package com.learning.spring.controllers;

import com.learning.spring.Dto.AuthTokenDto;
import com.learning.spring.Dto.LoginDto;
import com.learning.spring.Dto.StudentsDto;
import com.learning.spring.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthTokenDto> login(@RequestBody LoginDto loginDto){
        log.info("Received login request for user: {}", loginDto.getId());
        return new ResponseEntity<>(authService.authenticateUser(loginDto), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<StudentsDto> signUp(@RequestBody StudentsDto studentsDto){
        log.info("Received SignUo request for user: {}", studentsDto.getId());
        return new ResponseEntity<>(authService.createUser(studentsDto), HttpStatus.OK);
    }
}
