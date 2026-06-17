package com.pratik.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ResponseDTO {

    @JsonProperty("is_success")
    private boolean isSuccess;

    private String requestId;

    private List<String> oddNumbers;
    private List<String> evenNumbers;

    private List<String> alphabets;
    private List<String> specialCharacters;

    private String sum;

    private String largestNumber;
    private String smallestNumber;

    private int alphabetCount;
    private int numberCount;
    private int specialCharacterCount;

    private boolean containsDuplicates;

    private int vowelCount;

    private Map<String, Integer> alphabetFrequency;

    private int uniqueElementCount;

    private List<String> sortedNumbers;

    private String longestAlphabeticValue;
    private String shortestAlphabeticValue;

    private long processingTimeMs;

    private SummaryDTO summary;
}