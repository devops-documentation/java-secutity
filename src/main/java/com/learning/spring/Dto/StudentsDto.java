package com.learning.spring.Dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentsDto {
    private String id;
    private String name;
    private String password;
    private int age;
    private int year;
    private String role;
    private byte[] profile;
    private List<String> skills;

}
