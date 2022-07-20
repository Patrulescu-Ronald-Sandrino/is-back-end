package com.example.demo.repository;

import com.example.demo.domain.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstateRepository extends JpaRepository<Estate, Long> {
//    @Query("select distinct e from Estate e join fetch e.offers where (SELECT min(o.price) from e.offers o) < :price")
    @Query("select e from Estate e join e.offers where (SELECT min(o.price) from e.offers o) < :price")
    List<Estate> findAllByOfferPriceLessThan(double price);
}