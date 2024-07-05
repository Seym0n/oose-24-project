package com.sse.ooseproject.controllers;

import com.sse.ooseproject.EnrollmentRepository;
import com.sse.ooseproject.InstituteRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.exceptions.StudentValidationException;
import com.sse.ooseproject.models.Course;
import com.sse.ooseproject.models.Enrollment;
import com.sse.ooseproject.models.Student;
import com.sse.ooseproject.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;
    private final InstituteRepository instituteRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public StudentController(StudentRepository pStudentRepository, InstituteRepository pInstituteRepository, StudentValidator pStudentValidator, EnrollmentRepository pEnrollmentRepository) {
        this.studentRepository = pStudentRepository;
        this.instituteRepository = pInstituteRepository;
        this.studentValidator = pStudentValidator;
        this.enrollmentRepository = pEnrollmentRepository;
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

            studentValidator.validateStudent(student, false);


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

    @GetMapping("/student/edit")
    public Object editStudent(Model model, @RequestParam(name = "id", required = true) Long id){

        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            model.addAttribute("student", student);
        } else {
            return new RedirectView("/students");
        }

        model.addAttribute("page_type", "edit");
        model.addAttribute("study_subjects", instituteRepository.listStudySubjects());

        return "edit_student";
    }

    @PostMapping("/student/edit")
    public Object editStudentPOST(Model model, @ModelAttribute("student") Student student){
        Optional<Student> studentDB = studentRepository.findById(student.getId());
        if(studentDB.isPresent()){

            try {

                studentValidator.validateStudent(student, true);

                Student studentObj = studentDB.get();

                studentObj.setFirstName(student.getFirstName());
                studentObj.setLastName(student.getLastName());
                studentObj.setEmail(student.getEmail());
                studentObj.setMatNr(student.getMatNr());
                studentObj.setStudySubject(student.getStudySubject());

                studentRepository.save(student);


                model.addAttribute("student", studentObj);
                model.addAttribute("message_type", "success");
                model.addAttribute("message", "Der Studierende wurde bearbeitet.");
            } catch (StudentValidationException e){

                model.addAttribute("student", student);
                model.addAttribute("message_type", "error");
                model.addAttribute("message", e.getMessage());
            }

        } else {
            return new RedirectView("/students");
        }

        model.addAttribute("page_type", "edit");
        model.addAttribute("study_subjects", instituteRepository.listStudySubjects());

        return "edit_student";
    }

    @GetMapping("/student/enroll")
    public Object enrollStudent(Model model, @RequestParam(name = "id", required = true) Long id, @RequestParam(name = "semester", required = false) String semester){

        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            Student studentDB = student.get();

            model.addAttribute("student", studentDB);

            List<Enrollment> enrollments;
            if(semester != null){
                enrollments = enrollmentRepository.findByStudentIdAndSemester(id, semester);
            } else {
                enrollments = enrollmentRepository.findByStudentId(id);
            }

            List<Course> coursesAvail = instituteRepository.getCoursesByStudySubject(studentDB.getStudySubject());

            model.addAttribute("courses", coursesAvail);
            model.addAttribute("semester", semester);
            model.addAttribute("enrollments", enrollments);
        } else {
            return new RedirectView("/students");
        }


        return "enrollment";
    }
}
