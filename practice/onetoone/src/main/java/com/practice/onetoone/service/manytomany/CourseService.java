package com.practice.onetoone.service.manytomany;

import com.practice.onetoone.repository.manytomany.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

}
