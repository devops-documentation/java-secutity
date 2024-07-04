package com.learning.spring.service;

import com.learning.spring.Dto.AuthTokenDto;
import com.learning.spring.Dto.LoginDto;
import com.learning.spring.Dto.StudentsDto;
import com.learning.spring.Exception.AuthenticationFailedException;
import com.learning.spring.entity.Students;
import com.learning.spring.repository.StudentRepository;
import com.learning.spring.utils.DtoTOEntity;
import com.learning.spring.utils.EntityToDto;
import com.learning.spring.utils.PasswordVerify;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private Environment env;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityToDto entityToDto;

    @Autowired
    private DtoTOEntity dtoTOEntity;

    public StudentsDto createUser(StudentsDto studentsDto){
        log.info("Inside AuthService Class::Creating New User");
        return entityToDto.getStudents(studentRepository.save(dtoTOEntity.getStudentsDto(studentsDto)));
    }

    public AuthTokenDto authenticateUser(LoginDto dto){
        log.info("Inside LoginService::login for user: {}", dto.getId());
        Students user = studentRepository.findById(dto.getId())
                        .orElseThrow(() -> new AuthenticationFailedException("User doesn't exists"));
        log.info("account details fetched successfully");
        if (PasswordVerify.validatePassword(dto.getPassword(), user.getPassword())) {
            Map<String, Object> claimsMap = new HashMap<>();
            claimsMap.put("sample", "futureUse");
            claimsMap.put("id",user.getId());
            claimsMap.put("name", user.getName());
            log.info("password verified successfully and creating JWT token");
            // @formatter:off
            String token = Jwts
                    .builder()
                    .setId("learning")
                    .setSubject(user.getName())
                    .claim("authorities", Collections.singletonList(user.getRole()))
                    .addClaims(claimsMap)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()
                            + Long.parseLong(env.getProperty("JWT.token.validity"))))
                    .signWith(SignatureAlgorithm.HS512, env.getProperty("JWT.SECRET_KEY").getBytes())
                    .compact();
            // @formatter:on
            token = "Bearer " + token;
            return new AuthTokenDto(token);
        } else {
            log.error("Invalid password");
            throw new AuthenticationFailedException("Invalid password");
        }

    }
}
