package com.nagarro.uber.eats.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {
    ProductTraits product_traits;
    List<String> countries_of_origin;
}
