package com.pruebatecnica.backend.dtos;

import java.math.BigDecimal;

public interface VehicleDto {
    String getStatus();
    String getBrand();
    String getLine();
    String getModel();
    BigDecimal getMarketValue();
}
