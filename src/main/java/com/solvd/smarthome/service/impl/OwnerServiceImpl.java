package com.solvd.smarthome.service.impl;

import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.persistence.OwnerRepository;
import com.solvd.smarthome.persistence.impl.OwnerRepositoryImpl;
import com.solvd.smarthome.service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository = new OwnerRepositoryImpl();

    @Override
    public Owner create(Owner owner) {
        ownerRepository.create(owner);
        return owner;
    }

    @Override
    public Owner update(Owner owner) {
        ownerRepository.update(owner);
        return owner;
    }

    @Override
    public void delete(Long id) {
        ownerRepository.delete(id);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
