package com.minhan.databasedemo.controller;

import com.minhan.databasedemo.dao.StudentRepository;
import com.minhan.databasedemo.entity.Student;
import com.minhan.databasedemo.exceptions.custom.DuplicateEmailStudentException;
import com.minhan.databasedemo.exceptions.custom.StudentNotFoundException;
import com.minhan.databasedemo.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentRestController extends BaseController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentRestController(StudentRepository studentDao) {
        this.studentRepository = studentDao;
    }

    // get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // get student by id
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable Integer studentId) {
        Student studentFromDb = studentRepository.findById(studentId).get();

        if (null == studentFromDb) {
            throw new StudentNotFoundException();
        }

        return studentFromDb;
    }

    @PostMapping("/students")
    public ResponseEntity createNewStudent(@RequestBody Student student) {
        var hasStd = studentRepository.findByEmail(student.getEmail());

        if (null != hasStd) {
            throw new DuplicateEmailStudentException();
        }

        studentRepository.save(student);

        return Results.ok(Map.of("message", "Create student successfully"));
    }

    // update student by id
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student studentToUpdate) {
        return studentRepository.save(studentToUpdate);
    }

    // delete student by id
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Integer studentId) {
        Student studentFromDb = studentRepository.getById(studentId);

        if (null == studentFromDb) {
            throw new StudentNotFoundException();
        }

        studentRepository.deleteById(studentId);

        return Results.ok(Map.of("message", "Delete student successfully"));
    }
}
