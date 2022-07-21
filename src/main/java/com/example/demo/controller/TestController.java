package com.example.demo.controller;

import com.example.demo.domain.Agency;
import com.example.demo.domain.Estate;
import com.example.demo.domain.Offer;
import com.example.demo.repository.AgencyRepository;
import com.example.demo.repository.EstateRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.service.AgencyService;
import com.example.demo.service.EstateService;
import com.example.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


@CrossOrigin
@RestController
//@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE) // WARNING: 'produces' may lead to troubles later (probably when sending just strings?)
@RequestMapping(value = "/test")
public class TestController {

    //region fields
    @Autowired
    private AgencyRepository agencyRepository;
    
    @Autowired
    private EstateRepository estateRepository;
    
    @Autowired
    private OfferRepository offerRepository;
    
    @Autowired
    private AgencyService agencyService;

    @Autowired
    private EstateService estateService;

    @Autowired
    private OfferService offerService;

    private final Random r = new Random();
    //endregion
    
    //region endpoints
    @GetMapping("/runtime-exception")
    public void runtimeException(@RequestParam(defaultValue = "") String message) {
        throw new RuntimeException(message);
    }
    
    @PostMapping("/clear-database")
    public ResponseEntity<String> clearDatabase() {
        return ResponseEntity.ok(new HashMap<String, Long>() {
            {
                put("offers", offerService.deleteAll());
                put("agencies", agencyService.deleteAll());
                put("estates", estateService.deleteAll());
            }
        }.entrySet().stream()
                .map(entry -> String.format("Removed %d from %s.", entry.getValue(), entry.getKey()))
                .reduce("", (accumulator, x) -> accumulator += x + "\n"));
    }

    /** adds 2*number entries for the simple relation and number^2 entries the m:n relation */
    @PostMapping("/populate-database/{number}")
    public void populateDatabase(@PathVariable(required = false) Integer number) {
        if (number == null || number < 0) {
            number = 10;
        }

        List<Agency> agencies = new ArrayList<>();
        List<Estate> estates = new ArrayList<>();
        List<Offer> offers = new ArrayList<>();

        for (int i = 1; i <= number; i++) {
            agencies.add(generateAgency(i));
            estates.add(generateEstate(i));
        }

        agencies.forEach(agency -> {
            estates.forEach(estate -> {
                offers.add(generateOffer(agency, estate));
            });
        });

        for (int i = number + 1; i <= number * 2; i++) {
            agencies.add(generateAgency(i));
            estates.add(generateEstate(i));
        }

        agencyRepository.saveAllAndFlush(agencies);
        estateRepository.saveAllAndFlush(estates);
        offerRepository.saveAllAndFlush(offers);
    }
    //endregion

    //region level 2
    private Agency generateAgency(int i) {
        return new Agency(
                String.format("name%d", i),
                String.format("phone%d", i),
                String.format("email%d@email.com", i)
        );
    }

    private Estate generateEstate(int i) {
        return new Estate(
                String.format("address%d", i),
                Estate.Type.values()[i % Estate.Type.values().length],
                i * 1.1,
                i % 5,
                i % 2 == 0,
                LocalDate.parse(String.format("2022-%02d-%02d", i % 12 + 1, i % 28 + 1))
        );
    }

    private Offer generateOffer(Agency agency, Estate estate) {
        return new Offer(
                agency,
                estate,
                r.nextDouble() * 1000,
                LocalDate.parse(String.format("2022-%02d-%02d", r.nextInt(12) + 1, r.nextInt(28) + 1))
        );
    }
    //endregion
}
