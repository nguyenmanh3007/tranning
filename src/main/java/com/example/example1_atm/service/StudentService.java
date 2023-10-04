package com.example.example1_atm.service;


import com.example.example1_atm.entity.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<Student> filterByNameOrAgeOrGender(String name, int age, String gender, int pageNumber, int limit);
    List<Student> findAll(int pageNumber,int limit);
    List<Student> filterByNameOrAgeOrGender(String name, int age, String gender, Pageable pageable);
}
