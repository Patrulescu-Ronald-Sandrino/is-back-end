package com.example.demo.service;

import com.example.demo.domain.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> findAll();

    Offer findById(long id);

    void add(Offer offer);

    void update(Offer offer);

    void delete(long id);

    long deleteAll();

    List<Offer> findAllByAgency_Id(long agencyId);

    List<Offer> findAllByEstate_Id(long estateId);

    List<Offer> findAllByEstate_Address(String address);
}
