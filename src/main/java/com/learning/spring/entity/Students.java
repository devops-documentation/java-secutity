package com.learning.spring.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection  = "students")
public class Students {
    @Id
    private String id;
    private String name;
    private String password;
    private int age;
    private String role;
    private byte[] profile;
    private List<String> skills;

}
