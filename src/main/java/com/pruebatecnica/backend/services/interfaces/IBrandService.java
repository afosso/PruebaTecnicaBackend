package com.pruebatecnica.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.pruebatecnica.backend.models.Brand;

public interface IBrandService {
    
    List<Brand> getAll();

    Optional<Brand> getById(Integer id);

    Brand save(Brand brand) throws Exception;

    void update(Integer id, Brand brand) throws Exception;

    void delete(Integer id) throws Exception;

}
