package com.sse.ooseproject.controllers;

import com.sse.ooseproject.CourseRepository;
import com.sse.ooseproject.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository pCourseRepository){
        this.courseRepository = pCourseRepository;
    }

    @GetMapping("/courses")
    public String courses(Model model, @RequestParam(name = "sort_by", required = false, defaultValue = "id") String sortBy,
                            @RequestParam(name = "sort_asc", required = false, defaultValue = "true") boolean sortAsc) {

        Sort sort = sortAsc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Course> courses = this.courseRepository.findAll(sort);
        model.addAttribute("courses", courses);
        model.addAttribute("sort_by", sortBy);
        model.addAttribute("sort_asc", sortAsc);

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "courses";
    }
}
