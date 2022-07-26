package com.example.demo.service;

import com.example.demo.domain.Agency;
import com.example.demo.exceptions.EntityWithIdNotFoundException;
import com.example.demo.exceptions.ExceptionWithHttpStatus;
import com.example.demo.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyServiceImplementation implements AgencyService {

    //region fields
    @Autowired
    AgencyRepository agencyRepository;
    //endregion

    //region methods
    @Override
    public List<Agency> findAll() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency findById(long id) {
        return agencyRepository.findById(id)
                .orElseThrow(() -> new EntityWithIdNotFoundException(id));
    }

    @Override
    public Agency add(Agency agency) {
        return agencyRepository.saveAndFlush(agency);
    }

    @Override
    public void update(Agency agency) {
        findById(agency.getId());
        agencyRepository.saveAndFlush(agency);
    }

    @Override
    public void delete(long id) {
        agencyRepository.delete(findById(id));
    }

    @Override
    public long deleteAll() {
        long sizeBefore = agencyRepository.count();

        agencyRepository.deleteAll();
        long sizeAfter = agencyRepository.count();
        if (sizeAfter != 0) {
            throw new ExceptionWithHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to deleted all");
        }

        return sizeBefore;
    }

    @Override
    public List<Agency> findAllByNameContains(String substring) {
        return agencyRepository.findAllByNameContaining(substring);
    }
    //endregion
}
