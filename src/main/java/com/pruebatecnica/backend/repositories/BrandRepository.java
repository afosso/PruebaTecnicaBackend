package com.pruebatecnica.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.backend.models.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    
    @Query(value = "SELECT * FROM brands WHERE deleted_at IS NULL", nativeQuery = true)
    List<Brand> getAll();

    @Query(value = "SELECT * FROM brands WHERE deleted_at IS NULL AND id = :id", nativeQuery = true)
    Optional<Brand> findById(@Param("id") Integer id);
}
