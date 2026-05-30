package com.solvd.smarthome.persistence;

import com.solvd.smarthome.domain.Owner;
import java.util.List;

public interface OwnerRepository {
    void create(Owner owner);
    void update(Owner owner);
    void delete(Long id);
    Owner findById(Long id);
    List<Owner> findAll();
}
