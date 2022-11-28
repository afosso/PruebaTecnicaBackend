package com.pruebatecnica.backend.dtos;

import java.math.BigDecimal;

public interface VehicleDto {
    Integer getId();
    String getStatus();
    String getBrand();
    Integer getBrandId();
    String getLine();
    String getModel();
    BigDecimal getMarketValue();
}
