package com.example.example1_atm.repository;


import com.example.example1_atm.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
