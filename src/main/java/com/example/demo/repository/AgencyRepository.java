package com.example.demo.repository;

import com.example.demo.domain.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgencyRepository extends JpaRepository<Agency, Long> {
    List<Agency> findAllByNameContaining(String substring);
}