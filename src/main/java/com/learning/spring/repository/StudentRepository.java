package com.learning.spring.repository;

import com.learning.spring.entity.Students;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Students, String> {
}
