package com.pratik.bfhl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummaryDTO {

    private int totalElementsReceived;
    private int validElementsProcessed;
    private int invalidElementsIgnored;
}