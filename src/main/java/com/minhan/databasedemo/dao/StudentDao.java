package com.minhan.databasedemo.dao;

import com.minhan.databasedemo.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);
    Student getById(Integer id);
    Student findByEmail(String email);
    void deleteById(Integer id);
    List<Student> findAll();
    Student update(Student student);
    int deleteAll();
    long count();
}
