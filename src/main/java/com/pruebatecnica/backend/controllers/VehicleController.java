package com.pruebatecnica.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.backend.models.Vehicle;
import com.pruebatecnica.backend.services.interfaces.IVehicleService;

@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(value =  {"*"})
public class VehicleController {
    @Autowired
    private IVehicleService service;

    Map<String, Object> response = new HashMap<>();

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) Boolean status, 
                                    @RequestParam(required = false) Integer brandId,
                                    @RequestParam(required = false) String line,
                                    @RequestParam(required = false) String model) {
        try {
            response.put("status", true);
            response.put("message", "OK");
            response.put("data", this.service.getAllByFilters(status, brandId, line, model));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Vehicle vehicle) {
        try {
            Vehicle vehicleSaved = this.service.save(vehicle);

            response.put("status", true);
            response.put("message", "Registro almacenado");
            response.put("data", vehicleSaved);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
        try {
            this.service.update(id, vehicle);

            response.put("status", true);
            response.put("message", "Registro actualizado");
            response.put("data", null);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id) {
        try {
            this.service.delete(id);

            response.put("status", true);
            response.put("message", "Registro eliminado");
            response.put("data", null);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
