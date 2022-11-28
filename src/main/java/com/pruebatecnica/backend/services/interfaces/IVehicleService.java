package com.pruebatecnica.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.pruebatecnica.backend.dtos.VehicleDto;
import com.pruebatecnica.backend.models.Vehicle;

public interface IVehicleService {

    List<VehicleDto> getAllByFilters(Boolean status,Integer brandId,String line,String model);

    List<Vehicle> getAll();

    Optional<Vehicle> getById(Integer id);

    Vehicle save(Vehicle vehicle) throws Exception;

    void update(Integer id, Vehicle vehicle) throws Exception;

    void delete(Integer id) throws Exception;
}
