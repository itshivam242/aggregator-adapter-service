package com.nagarro.spi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nagarro.enums.Tag;
import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    String id;
    Map<Locale,String> name;

    private List<CalorieInfo> calorieInfo;
    Map<Locale,String> description;
    List<Tag> tags;
    double price;
    double taxRate;
    List<String> modifierGroups;
    String imageUrl;
}
