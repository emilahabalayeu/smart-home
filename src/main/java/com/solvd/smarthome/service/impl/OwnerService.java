package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.Owner;
import java.util.List;

public interface OwnerService {
    Owner create(Owner owner);
    Owner update(Owner owner);
    void delete(Long id);
    Owner findById(Long id);
    List<Owner> findAll();
}
