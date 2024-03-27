package com.nagarro.spi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    public String id;
    public Map<String, String> name;
    public List<String> categoryIds;
}
