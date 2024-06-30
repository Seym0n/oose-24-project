package com.sse.ooseproject.controllers;

import com.sse.ooseproject.InstituteRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.exceptions.StudentValidationException;
import com.sse.ooseproject.models.Student;
import com.sse.ooseproject.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;
    private final InstituteRepository instituteRepository;

    @Autowired
    public StudentController(StudentRepository pStudentRepository, InstituteRepository pInstituteRepository, StudentValidator pStudentValidator) {
        this.studentRepository = pStudentRepository;
        this.instituteRepository = pInstituteRepository;
        this.studentValidator = pStudentValidator;
    }

    @GetMapping("/students")
    public String students(Model model, @RequestParam(name = "sort_by", required = false, defaultValue = "firstName") String sortBy,
                           @RequestParam(name = "sort_asc", required = false, defaultValue = "true") boolean sortAsc) {

        Sort sort = sortAsc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Student> students = studentRepository.findAll(sort);
        model.addAttribute("students", students);
        model.addAttribute("sort_by", sortBy);
        model.addAttribute("sort_asc", sortAsc);

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "students";
    }

    @GetMapping("/student/new")
    public String getNewStudent(Model model){

        Student student = new Student();

        model.addAttribute("student", student);
        model.addAttribute("page_type", "new");


        model.addAttribute("study_subjects", instituteRepository.listStudySubjects());

        return "edit_student";
    }

    @PostMapping("/student/new")
    public String addStudent(Model model, @ModelAttribute("student") Student student) {

        try {

            studentValidator.validateStudent(student);


            student = new Student(student);

            studentRepository.save(student);


            student = new Student();

            model.addAttribute("student", student);
            model.addAttribute("message_type", "success");
            model.addAttribute("message", "Der Studierende wurde hinzugefügt.");
        } catch (StudentValidationException e){

            model.addAttribute("student", student);
            model.addAttribute("message_type", "error");
            model.addAttribute("message", e.getMessage());
        }

        model.addAttribute("page_type", "new");
        model.addAttribute("study_subjects", instituteRepository.listStudySubjects());

        return "edit_student";
    }
}
