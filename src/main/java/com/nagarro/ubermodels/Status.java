package com.nagarro.ubermodels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {
    private String status;
    private String is_offline_until;
    private String reason;
}