package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student extends Person {
    // Note: This class does not need its own id attribute as that will be derived.
    private long matNr;
    private String studySubject;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollmentList;

    public Student(){
        super();
    }

    public Student(String pFirstName, String pLastName, String pEmail, long pMatNr, String pStudySubject, University pUniversity){
        super(pFirstName, pLastName, pEmail, pUniversity);
        this.matNr = pMatNr;
        this.studySubject = pStudySubject;
    }

    // Getter & Setter methods

    public long getMatNr() {
        return this.matNr;
    }

    public String getStudySubject() {
        return this.studySubject;
    }

    public void setMatNr(long matNr) {
        this.matNr = matNr;
    }

    public void setStudySubject(String studySubject) {
        this.studySubject = studySubject;
    }

    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }
}
