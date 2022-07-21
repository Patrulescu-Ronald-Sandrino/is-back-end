package com.example.demo.controller;

import com.example.demo.domain.Offer;
import com.example.demo.exceptions.ExceptionWithHttpStatus;
import com.example.demo.service.OfferService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( value = "/offers")
public class OfferController {
    //region fields
    @Autowired
    OfferService offerService;
    //endregion

    //region endpoints
    @GetMapping
    public ResponseEntity<List<Offer>> findAll() {
        return ResponseEntity.ok(offerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> findById(@PathVariable long id) {
        return ResponseEntity.ok(offerService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Offer offer) {
        offerService.add(offer);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Offer offer, @PathVariable long id) {
        if (offer.getId() != id) {
            throw new ExceptionWithHttpStatus(HttpStatus.BAD_REQUEST, "id mismatch");
//            throw new RuntimeException("id mismatch");
        }
        offerService.update(offer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        offerService.delete(id);
    }

    @DeleteMapping()
    public ResponseEntity<Long> deleteAll() {
        return ResponseEntity.ok(offerService.deleteAll());
    }


    @GetMapping("/by-agency-id/{agencyId}")
    public ResponseEntity<List<Offer>> findAllByAgency_Id(@PathVariable long agencyId) {
        return ResponseEntity.ok(offerService.findAllByAgency_Id(agencyId));
    }
    
    @GetMapping("/by-estate-id/{estateId}")
    public ResponseEntity<List<Offer>> findAllByEstate_Id(@PathVariable long estateId) {
        return ResponseEntity.ok(offerService.findAllByEstate_Id(estateId));
    }

    @GetMapping("/by-estate-address/{address}")
    public ResponseEntity<List<Offer>> findAllByEstate_Address(@PathVariable String address) {
        return ResponseEntity.ok(offerService.findAllByEstate_Address(address));
    }
    //endregion
}
