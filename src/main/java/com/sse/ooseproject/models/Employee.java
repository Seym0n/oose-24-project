package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends Person {
    private long staffNr;
    private boolean isProfessor;

    @OneToMany(mappedBy = "employee")
    private List<TeachingShift> teachingShiftList;

    public Employee(){
        super();
    }

    public Employee(String pFirstName, String pLastName, String pEmail, long pStaffNr, boolean pIsProfessor, University pUniversity){
        super(pFirstName, pLastName, pEmail, pUniversity);
        this.staffNr = pStaffNr;
        this.isProfessor = pIsProfessor;
    }

    // Getter & Setter methods

    public long getStaffNr() {
        return this.staffNr;
    }

    public boolean getIsProfessor(){
        return this.isProfessor;
    }

    public void setStaffNr(long staffNr) {
        this.staffNr = staffNr;
    }

    public void setIsProfessor(boolean pIsProfessor){
        this.isProfessor = pIsProfessor;
    }

    public List<TeachingShift> getTeachingShiftList() {
        return teachingShiftList;
    }

    public void setTeachingShiftList(List<TeachingShift> teachingShiftList) {
        this.teachingShiftList = teachingShiftList;
    }
}
