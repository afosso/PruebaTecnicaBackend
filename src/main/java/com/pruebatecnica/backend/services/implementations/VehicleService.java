package com.pruebatecnica.backend.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.backend.dtos.VehicleDto;
import com.pruebatecnica.backend.models.Vehicle;
import com.pruebatecnica.backend.repositories.VehicleRepository;
import com.pruebatecnica.backend.services.interfaces.IVehicleService;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private VehicleRepository repository;

    @Override
    public List<Vehicle> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<VehicleDto> getAllByFilters(Boolean status, Integer brandId, String line, String model) {
        return this.repository.getAllByFilters(status, brandId, line, model);
    }

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        vehicle.setCreatedAt(LocalDateTime.now());
        return this.repository.save(vehicle);
    }

    @Override
    public void update(Integer id, Vehicle vehicle) throws Exception {
        Optional<Vehicle> vehicleO = this.repository.findById(id);
        if (vehicleO.isEmpty()) {
            throw new Exception("No se encontró un registro");
        }
        
        Vehicle vehicleSaved = vehicleO.get();
        vehicleSaved.setBrand(vehicle.getBrand());
        vehicleSaved.setLine(vehicle.getLine());
        vehicleSaved.setMarketValue(vehicle.getMarketValue());
        vehicleSaved.setModel(vehicle.getModel());
        vehicleSaved.setStatus(vehicle.getStatus());
        vehicleSaved.setUpdatedAt(LocalDateTime.now());

        this.repository.save(vehicleSaved);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Optional<Vehicle> vehicleO = this.repository.findById(id);
        if (vehicleO.isEmpty()) {
            throw new Exception("No se encontró un registro");
        }
        Vehicle vehicleSaved = vehicleO.get();
        vehicleSaved.setDeletedAt(LocalDateTime.now());

        this.repository.save(vehicleSaved);
    }

}
