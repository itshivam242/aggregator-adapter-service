package com.nagarro.spi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreHoursPerDisposition {
    private String disposition;
    private List<StoreHours> storeHours;
}
