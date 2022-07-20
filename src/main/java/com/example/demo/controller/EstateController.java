package com.example.demo.controller;

import com.example.demo.domain.Estate;
import com.example.demo.exceptions.ExceptionWithHttpStatus;
import com.example.demo.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/estates")
public class EstateController {
    //region fields
    @Autowired
    EstateService estateService;
    //endregion

    //region endpoints
    @GetMapping
    public ResponseEntity<List<Estate>> findAll() {
        return ResponseEntity.ok(estateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estate> findById(@PathVariable long id) {
        return ResponseEntity.ok(estateService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Estate estate) {
        estateService.add(estate);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Estate estate, @PathVariable long id) {
        if (estate.getId() != id) {
            throw new ExceptionWithHttpStatus(HttpStatus.BAD_REQUEST, "id mismatch");
//            throw new RuntimeException("id mismatch");
        }
        estateService.update(estate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        estateService.delete(id);
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteAll() {
        return ResponseEntity.ok(estateService.deleteAll());
    }

    @GetMapping("/by-offer-price-less-than/{price}")
    public ResponseEntity<List<Estate>> findAllByOfferPriceLessThan(@PathVariable double price) {
        return ResponseEntity.ok(estateService.findAllByOfferPriceLessThan(price));
    }
    //endregion
}
