package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String semester;

    public Enrollment(){

    }

    public Enrollment(Course pCourse, Student pStudent, String pSemester){
        this.course = pCourse;
        this.student = pStudent;
        this.semester = pSemester;
    }

    // Getter & Setter methods

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
