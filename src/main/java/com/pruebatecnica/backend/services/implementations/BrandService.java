package com.pruebatecnica.backend.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.backend.models.Brand;
import com.pruebatecnica.backend.repositories.BrandRepository;
import com.pruebatecnica.backend.repositories.VehicleRepository;
import com.pruebatecnica.backend.services.interfaces.IBrandService;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository repository;

    @Autowired
    private VehicleRepository repositoryVehicle;

    @Override
    public List<Brand> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Optional<Brand> getById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Brand save(Brand brand) throws Exception {
        brand.setCreatedAt(LocalDateTime.now());
        return this.repository.save(brand);
    }

    @Override
    public void update(Integer id, Brand brand) throws Exception {
        Optional<Brand> brandO = this.repository.findById(id);
        if (brandO.isEmpty()) {
            throw new Exception("No se encontró un registro");
        }

        Brand brandSaved = brandO.get();
        brandSaved.setName(brand.getName());
        brandSaved.setStatus(brand.getStatus());
        brandSaved.setUpdatedAt(LocalDateTime.now());

        this.repository.save(brandSaved);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Optional<Brand> brandO = this.repository.findById(id);
        if (brandO.isEmpty()) {
            throw new Exception("No se encontró un registro");
        }

        if (this.repositoryVehicle.findByBrand(brandO.get()).size() > 0) {
            throw new Exception("No se puede eliminar porque está relacionado en vehículos");
        }

        Brand brandSaved = brandO.get();
        brandSaved.setDeletedAt(LocalDateTime.now());
        this.repository.save(brandSaved);
    }
    
}
