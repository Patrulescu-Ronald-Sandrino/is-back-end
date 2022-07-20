package com.example.demo.service;

import com.example.demo.domain.Estate;

import java.util.List;

public interface EstateService {
    List<Estate> findAll();

    Estate findById(long id);

    void add(Estate estate);

    void update(Estate estate);

    void delete(long id);

    long deleteAll();

    List<Estate> findAllByOfferPriceLessThan(double price);
}
