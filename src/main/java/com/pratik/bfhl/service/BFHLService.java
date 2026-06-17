package com.pratik.bfhl.service;

import com.pratik.bfhl.dto.RequestDTO;
import com.pratik.bfhl.dto.ResponseDTO;

public interface BFHLService {

    ResponseDTO processData(
            RequestDTO request,
            String requestId,
            long startTime
    );
}