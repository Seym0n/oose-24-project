package com.sse.ooseproject.controllers;

import com.sse.ooseproject.CourseRepository;
import com.sse.ooseproject.EnrollmentRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.models.Course;
import com.sse.ooseproject.models.Enrollment;
import com.sse.ooseproject.models.EnrollmentId;
import com.sse.ooseproject.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public EnrollmentController(EnrollmentRepository pEnrollmentRepository, CourseRepository pCourseRepository, StudentRepository pStudentRepository){
        this.enrollmentRepository = pEnrollmentRepository;
        this.courseRepository = pCourseRepository;
        this.studentRepository = pStudentRepository;
    }


    @GetMapping("/enrollment/enroll")
    public RedirectView enrollCourse(Model model, @RequestParam(name = "student_id", required = true) Long pStudentId,
                                            @RequestParam(name = "course_id", required = true) Long pCourseId,
                                            @RequestParam(name = "semester", required = true) String pSemester){
        Enrollment enrollment = new Enrollment();

        Optional<Course> course = this.courseRepository.findById(pCourseId);
        Optional<Student> student = this.studentRepository.findById(pStudentId);
        if(course.isEmpty() || student.isEmpty()){
            return new RedirectView("/student/enroll?id=" + pStudentId + "&semester="+pSemester);
        }

        Course courseDB = course.get();
        Student studentDB = student.get();

        enrollment.setCourse(courseDB);
        enrollment.setStudent(studentDB);
        enrollment.setSemester(pSemester);

        EnrollmentId id = new EnrollmentId(pCourseId, pStudentId);

        enrollment.setId(id);
        enrollmentRepository.save(enrollment);

        return new RedirectView("/student/enroll?id=" + pStudentId + "&semester="+pSemester);

    }

    @GetMapping("/enrollment/delete")
    RedirectView deleteEnrollment(Model model, @RequestParam(name = "student_id", required = true) Long pStudentId,
                                  @RequestParam(name = "course_id", required = true) Long pCourseId,
                                  @RequestParam(name = "semester", required = true) String pSemester){
        this.enrollmentRepository.deleteEnrollment(pStudentId, pCourseId, pSemester);

        return new RedirectView("/student/enroll?id=" + pStudentId + "&semester="+pSemester);
    }
}
