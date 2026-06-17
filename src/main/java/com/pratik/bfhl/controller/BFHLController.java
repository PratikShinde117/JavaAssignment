package com.pratik.bfhl.controller;

import com.pratik.bfhl.dto.RequestDTO;
import com.pratik.bfhl.dto.ResponseDTO;
import com.pratik.bfhl.service.BFHLService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BFHLController {

    private final BFHLService service;

    @PostMapping("/bfhl")
    public ResponseEntity<ResponseDTO> process(
            @RequestBody RequestDTO request) {

        return ResponseEntity.ok(
                service.processData(request)
        );
    }
}