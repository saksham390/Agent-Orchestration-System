package com.example.aistudyassistant.repository;

import com.example.aistudyassistant.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
