package com.pruebatecnica.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pruebatecnica.backend.models.Vehicle;
import com.pruebatecnica.backend.services.interfaces.IBrandService;
import com.pruebatecnica.backend.services.interfaces.IVehicleService;

@SpringBootTest
class VehicleSaveTest {
	
	@Autowired
	private IVehicleService service;
	
	@Autowired
	private IBrandService serviceBrand;
	

	@Test
	void test() throws Exception {
		
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(serviceBrand.getById(1).orElseThrow());
		vehicle.setCreatedAt(LocalDateTime.now());
		vehicle.setLine("CX30");
		vehicle.setMarketValue(BigDecimal.valueOf(85000000));
		vehicle.setModel("2023");
		vehicle.setStatus(true);
		
		Vehicle vehicleSaved = this.service.save(vehicle);
		
		assertThat(vehicleSaved.getId()).isNotEqualTo(0);
	}

}
