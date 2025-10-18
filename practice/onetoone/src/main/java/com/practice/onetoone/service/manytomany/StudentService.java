package com.practice.onetoone.service.manytomany;

import com.practice.onetoone.entity.manytomany.Course;
import com.practice.onetoone.entity.manytomany.Student;
import com.practice.onetoone.repository.manytomany.CourseRepository;
import com.practice.onetoone.repository.manytomany.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

//    public Student saveStudentWithCourses(String studentName, Set<Integer> courseIds) {
//        Student student = new Student();
//        student.setName(studentName);
//
//        List<Course> selectedCourses = courseRepository.findAllById(courseIds);
//        student.setCourses(selectedCourses);
//
//        return studentRepository.save(student);
//    }

    public Student saveStudentWithCourses(Student student){
        Student temp = studentRepository.save(student);
        System.out.println(temp.getName() + temp.getCourses() + "----------------");
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}
