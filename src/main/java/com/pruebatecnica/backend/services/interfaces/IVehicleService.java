package com.pruebatecnica.backend.services.interfaces;

import java.util.List;

import com.pruebatecnica.backend.models.Vehicle;

public interface IVehicleService {
    List<Vehicle> getAll();

    Vehicle save(Vehicle vehicle) throws Exception;

    void update(Integer id, Vehicle vehicle) throws Exception;

    void delete(Integer id) throws Exception;
}
