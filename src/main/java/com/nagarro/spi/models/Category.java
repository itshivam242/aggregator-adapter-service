package com.nagarro.spi.models;

import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
public class Category {
    String id;
    Map<Locale,String> name;
    List<String> itemIds;
}
