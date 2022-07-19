package com.example.demo.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "offers", uniqueConstraints = { @UniqueConstraint(name = "unique_agency_and_estate", columnNames = {"ref_agency", "ref_estate"}) })
public class Offer {

    //region fields
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_agency")
    private Agency agency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_estate")
    private Estate estate;

    private double price;

    @Column(name = "create_date")
    private LocalDate createDate;
    //endregion

    //region constructors
    public Offer(long id, Agency agency, Estate estate, double price, LocalDate createDate) {
        this.id = id;
        this.agency = agency;
        this.estate = estate;
        this.price = price;
        this.createDate = createDate;
    }

    public Offer() {
    }

    public Offer(Agency agency, Estate estate, double price, LocalDate createDate) {
        this.agency = agency;
        this.estate = estate;
        this.price = price;
        this.createDate = createDate;
    }
    //endregion

    //region getters
    public long getId() {
        return id;
    }

    public Agency getAgency() {
        return agency;
    }

    public Estate getEstate() {
        return estate;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }
    //endregion
}
