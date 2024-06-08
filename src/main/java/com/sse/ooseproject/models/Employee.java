package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends Person {
    private long staffNr;
    private boolean isProfessor;

    public Employee(){
        super();
    }

    public Employee(String pFirstName, String pLastName, String pEmail, long pStaffNr, boolean pIsProfessor){
        super(pFirstName, pLastName, pEmail);
        this.staffNr = pStaffNr;
        this.isProfessor = pIsProfessor;
    }

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
}
