package com.nagarro.spi.models;

import lombok.Data;

@Data
public class Quantity {
    int min;
    int max;
    int defaultQuantity;
}
