package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;
    private int seats;
    private boolean isAuditorium;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "room")
    private List<RoomOccupancy> occupancies;


    public Room(){

    }

    public Room(String pNumber, int pSeats, boolean pIsAuditorium, Building pBuilding, List<RoomOccupancy> pRoomOccupancy){
        this.number = pNumber;
        this.isAuditorium = pIsAuditorium;
        this.seats = pSeats;
        this.building = pBuilding;
        this.occupancies = pRoomOccupancy;
    }

    // Getter & Setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean getIsAuditorium() {
        return isAuditorium;
    }

    public void setIsAuditorium(boolean auditorium) {
        this.isAuditorium = auditorium;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<RoomOccupancy> getOccupancies() {
        return occupancies;
    }

    public void setOccupancies(List<RoomOccupancy> occupancies) {
        this.occupancies = occupancies;
    }
}
