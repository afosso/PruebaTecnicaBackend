package com.pruebatecnica.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.backend.models.Brand;
import com.pruebatecnica.backend.models.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
 
    List<Vehicle> findByBrand(Brand brand);
}
