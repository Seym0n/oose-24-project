package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "chair")
public class Chair extends OrganizationalUnit {

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;


    private Employee owner;

    private Building building;

    public Chair(){

    }

    public Chair(String pName, Employee pOwner, Building pBuilding){
        super(pName);
        this.owner = pOwner;
        this.building = pBuilding;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
