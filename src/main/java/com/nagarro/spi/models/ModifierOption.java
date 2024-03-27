package com.nagarro.spi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nagarro.enums.Tag;
import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifierOption {
    String id;
    Map<Locale,String> name;
    Map<Locale,String> description;
    int price;
    List<Tag> tags;
    List<Object> modifierGroups;
}
