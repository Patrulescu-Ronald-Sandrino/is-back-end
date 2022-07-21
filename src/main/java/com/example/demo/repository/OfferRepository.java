package com.example.demo.repository;

import com.example.demo.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByAgency_Id(long agencyId);

    List<Offer> findAllByEstate_Id(long estateId);

    List<Offer> findAllByEstate_Address(String address);
}