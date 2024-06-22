package com.sse.ooseproject.models;

import jakarta.persistence.*;

@MappedSuperclass
public class OrganizationalUnit {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public OrganizationalUnit(){

    }

    public OrganizationalUnit(String pName){
        this.name = pName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
