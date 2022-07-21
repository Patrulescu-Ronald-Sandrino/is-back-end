package com.example.demo.repository;

import com.example.demo.domain.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstateRepository extends JpaRepository<Estate, Long> {
    // TODO LATER: remove commented out code
//    @Query("select distinct e from Estate e join fetch e.offers where (SELECT min(o.price) from e.offers o) < :price")
//    @Query("select e from Estate e join e.offers where (SELECT min(o.price) from e.offers o) < :price")
//    @Query("select distinct e from Estate e inner join Offer o on o.estate.id = e.id ")
//    @Query("select e from Estate e where (select e1 from Estate e1 inner join Offer o on o.estate.id = e1.id) < :price")
    @Query("SELECT e FROM Estate e INNER JOIN Offer o ON e.id = o.estate.id" +
            "   GROUP BY e.id" +
            "   HAVING MIN(o.price) < :price")
    List<Estate> findAllByOfferPriceLessThan(double price);

}