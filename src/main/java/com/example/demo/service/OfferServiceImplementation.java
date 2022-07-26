package com.example.demo.service;

import com.example.demo.domain.Offer;
import com.example.demo.exceptions.EntityWithIdNotFoundException;
import com.example.demo.exceptions.ExceptionWithHttpStatus;
import com.example.demo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImplementation implements OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public Offer findById(long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityWithIdNotFoundException(id));
    }

    @Override
    public Offer add(Offer offer) {
        return offerRepository.saveAndFlush(offer);
    }

    @Override
    public void update(Offer offer) {
        findById(offer.getId());
        offerRepository.saveAndFlush(offer);
    }

    @Override
    public void delete(long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public long deleteAll() {
        long sizeBefore = offerRepository.count();

        offerRepository.deleteAll();
        long sizeAfter = offerRepository.count();
        if (sizeAfter != 0) {
            throw new ExceptionWithHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to deleted all");
        }

        return sizeBefore;
    }

    @Override
    public List<Offer> findAllByAgency_Id(long agencyId) {
        return offerRepository.findAllByAgency_Id(agencyId);
    }

    @Override
    public List<Offer> findAllByEstate_Id(long estateId) {
        return offerRepository.findAllByEstate_Id(estateId);
    }

    @Override
    public List<Offer> findAllByEstate_Address(String address) {
        return offerRepository.findAllByEstate_Address(address);
    }
}
