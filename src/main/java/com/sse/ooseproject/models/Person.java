package com.sse.ooseproject.models;

import jakarta.persistence.*;

@MappedSuperclass
public class Person {
    // Entities or MappedSuperclass models in Spring require an id. The @GeneratedValue annotation makes sure
    // that the id is automatically increased when inserting new objects into the database.
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;


    /**
     * A Person object. Spring requires an empty constructor. Do not change this but rather implement another
     * plausible constructor.
     */
    public Person() {}

    public Person(String pFirstName, String pLastName, String pEmail, University pUniversity){
        this.firstName = pFirstName;
        this.lastName = pLastName;
        this.email = pEmail;
        this.university = pUniversity;
    }

    // Getter & Setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
