package com.example.demo.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estates", indexes = @Index(name = "unique_address", columnList = "address", unique = true))
public class Estate {

    //region fields
    @Id
    @GeneratedValue
    private Long id;

    private String address;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double surface;

    private int rooms;

    private boolean detached;

    @Column(name = "last_revision_date")
    private LocalDate lastRevisionDate;

    //endregion

    //region constructors
    public Estate(long id, String address, Type type, double surface, int rooms, boolean detached, LocalDate lastRevisionDate) {
        this.id = id;
        this.address = address;
        this.type = type;
        this.surface = surface;
        this.rooms = rooms;
        this.detached = detached;
        this.lastRevisionDate = lastRevisionDate;
    }

    public Estate() {
    }

    public Estate(String address, Type type, double surface, int rooms, boolean detached, LocalDate lastRevisionDate) {
        this.address = address;
        this.type = type;
        this.surface = surface;
        this.rooms = rooms;
        this.detached = detached;
        this.lastRevisionDate = lastRevisionDate;
    }
    //endregion

    //region getters
    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Type getType() {
        return type;
    }

    public double getSurface() {
        return surface;
    }

    public int getRooms() {
        return rooms;
    }

    public boolean isDetached() {
        return detached;
    }

    public LocalDate getLastRevisionDate() {
        return lastRevisionDate;
    }

    //endregion

    public enum Type {
        apartment,
        house
    }
}
