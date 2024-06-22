package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "teaching_shift")
public class TeachingShift {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public TeachingShift(){

    }

    public TeachingShift(Course pCourse, Employee pEmployee){
        this.course = pCourse;
        this.employee = pEmployee;
    }

    // Getter & Setter methods

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
