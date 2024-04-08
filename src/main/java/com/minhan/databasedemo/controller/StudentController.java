package com.minhan.databasedemo.controller;

import com.minhan.databasedemo.dao.StudentRepository;
import com.minhan.databasedemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentDao) {
        this.studentRepository = studentDao;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/students/list";
    }

    @GetMapping("/students/list")
    public String listEmployees(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "student-list";
    }

    @GetMapping("/students/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/students/save")
    public String showFormForAdd(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/students/showFormForUpdate")
    public String showFormForAdd(@RequestParam("studentId") Integer studentId,
                                 Model model) {
        model.addAttribute("student", studentRepository.findById(studentId));
        return "student-form";
    }

    @GetMapping("/students/delete")
    public String delete(@RequestParam("studentId") Integer studentId) {

        var std = studentRepository.findById(studentId);

        if (std.isPresent()) {
            studentRepository.delete(std.get());
        }

        return "redirect:/students/list";
    }
}
