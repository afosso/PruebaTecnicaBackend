package com.pruebatecnica.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.backend.dtos.VehicleDto;
import com.pruebatecnica.backend.models.Brand;
import com.pruebatecnica.backend.models.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
 
    List<Vehicle> findByBrand(Brand brand);

    @Query(value = "SELECT "
                + "    vehi.id,"
                + "    CASE WHEN vehi.status THEN 'Nuevo' ELSE 'Usado' END AS status,"
                + "    vehi.brand_id AS brandId,"
                + "    bra.name AS brand,"
                + "    vehi.line,"
                + "    vehi.model,"
                + "    vehi.market_value AS marketValue"
                + " FROM vehicles vehi"
                + " INNER JOIN brands bra ON bra.id = vehi.brand_id"
                + " WHERE vehi.deleted_at IS NULL AND (vehi.status = :status OR :status IS NULL) AND "
                + " (vehi.brand_id = :brandId OR :brandId IS NULL) AND "
                + " (vehi.line = :line OR :line IS NULL) AND"
                + " (vehi.model = :model OR :model IS NULL)", nativeQuery = true)
    List<VehicleDto> getAllByFilters(@Param("status") Boolean status, @Param("brandId") Integer brandId, @Param("line") String line, @Param("model") String model);
}
