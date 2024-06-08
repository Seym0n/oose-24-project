package com.sse.ooseproject.controllers;

import com.sse.ooseproject.EmployeeRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.models.Employee;
import com.sse.ooseproject.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository pEmployeeRepository) {
        this.employeeRepository = pEmployeeRepository;
    }

    @GetMapping("/employees")
    public String employees(Model model, @RequestParam(name = "sort_by", required = false, defaultValue = "firstName") String sortBy,
                           @RequestParam(name = "sort_asc", required = false, defaultValue = "true") boolean sortAsc) {
        // TODO add functionality.

        Sort sort = sortAsc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Employee> employees = this.employeeRepository.findAll(sort);
        model.addAttribute("employees", employees);
        model.addAttribute("sort_by", sortBy);
        model.addAttribute("sort_asc", sortAsc);

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "employees";
    }
}
