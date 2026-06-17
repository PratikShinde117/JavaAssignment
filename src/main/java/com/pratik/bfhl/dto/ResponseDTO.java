package com.pratik.bfhl.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDTO {

    private boolean isSuccess;

    private List<String> oddNumbers;

    private List<String> evenNumbers;

    private List<String> alphabets;

    private List<String> specialCharacters;
}