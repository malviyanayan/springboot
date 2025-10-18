package com.practice.onetoone.repository.manytomany;

import com.practice.onetoone.entity.manytomany.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
