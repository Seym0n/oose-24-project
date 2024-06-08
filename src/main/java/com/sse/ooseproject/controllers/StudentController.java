package com.sse.ooseproject.controllers;

import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository pStudentRepository) {
        this.studentRepository = pStudentRepository;
    }

    @GetMapping("/students")
    public String students(Model model, @RequestParam(name = "sort_by", required = false, defaultValue = "firstName") String sortBy,
                           @RequestParam(name = "sort_asc", required = false, defaultValue = "true") boolean sortAsc) {
        // TODO add functionality.

        Sort sort = sortAsc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Student> students = studentRepository.findAll(sort);
        model.addAttribute("students", students);
        model.addAttribute("sort_by", sortBy);
        model.addAttribute("sort_asc", sortAsc);

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "students";
    }
}
