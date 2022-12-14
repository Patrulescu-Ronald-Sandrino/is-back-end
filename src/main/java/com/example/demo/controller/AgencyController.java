package com.example.demo.controller;

import com.example.demo.domain.Agency;
import com.example.demo.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/agencies")
public class AgencyController {

    //region fields
    @Autowired
    private AgencyService agencyService;
    //endregion

    //region endpoints
    @GetMapping
    public ResponseEntity<List<Agency>> findAll() {
        return ResponseEntity.ok(agencyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agency> findById(@PathVariable long id) {
        return ResponseEntity.ok(agencyService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agency add(@RequestBody Agency agency) {
        return agencyService.add(agency);
    }

    @PutMapping
    public void update(@RequestBody Agency agency) {
        agencyService.update(agency);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        agencyService.delete(id);
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteAll() {
        return ResponseEntity.ok(agencyService.deleteAll());
    }

    @GetMapping("/by-name-contains/{substring}")
    public List<Agency> findAllByNameContains(@PathVariable String substring) {
        return agencyService.findAllByNameContains(substring);
    }
    //endregion
}
