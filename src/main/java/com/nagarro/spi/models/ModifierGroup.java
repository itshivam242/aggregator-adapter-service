package com.nagarro.spi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifierGroup {
    String id;
    Map<Locale,String> name;
    List<String> modifierOptions;
    Quantity quantity;
    List<String> defaultOptions;
}
