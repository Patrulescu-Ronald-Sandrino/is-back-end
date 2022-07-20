package com.example.demo.service;

import com.example.demo.domain.Estate;
import com.example.demo.exceptions.EntityWithIdNotFoundException;
import com.example.demo.exceptions.ExceptionWithHttpStatus;
import com.example.demo.repository.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateServiceImplementation implements EstateService {
    //region fields
    @Autowired
    private EstateRepository estateRepository;
    //endregion

    //region methods
    @Override
    public List<Estate> findAll() {
        return estateRepository.findAll();
    }

    @Override
    public Estate findById(long id) {
        return estateRepository.findById(id)
                .orElseThrow(() -> new EntityWithIdNotFoundException(id));
    }

    @Override
    public void add(Estate estate) {
        estateRepository.saveAndFlush(estate);
    }

    @Override
    public void update(Estate estate) {
        findById(estate.getId());
        estateRepository.saveAndFlush(estate);
    }

    @Override
    public void delete(long id) {
        estateRepository.delete(findById(id));
    }

    @Override
    public long deleteAll() {
        long sizeBefore = estateRepository.count();

        estateRepository.deleteAll();
        long sizeAfter = estateRepository.count();
        if (sizeAfter != 0) {
            throw new ExceptionWithHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to deleted all");
        }

        return sizeBefore;
    }

    @Override
    public List<Estate> findAllByOfferPriceLessThan(double price) {
        return estateRepository.findAllByOfferPriceLessThan(price);
    }
    //endregion
}
