package com.practice.onetoone.repository.manytomany;

import com.practice.onetoone.entity.manytomany.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
