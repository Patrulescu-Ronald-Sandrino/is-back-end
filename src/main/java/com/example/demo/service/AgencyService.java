package com.example.demo.service;

import com.example.demo.domain.Agency;

import java.util.List;

public interface AgencyService {
    List<Agency> findAll();

    Agency findById(long id);

    void add(Agency agency);

    void update(Agency agency);

    void delete(long id);

    long deleteAll();

    List<Agency> findAllByNameContains(String substring);
}
