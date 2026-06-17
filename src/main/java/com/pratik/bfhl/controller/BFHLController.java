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

    @GetMapping("/")
    public String home() {
        return "BFHL API Running Successfully V2";
    }

    @PostMapping("/health")
public ResponseEntity<String> health() {
    return ResponseEntity.ok("UP");
}

    @PostMapping("/bfhl")
    public ResponseEntity<ResponseDTO> process(
            @RequestBody RequestDTO request,
            @RequestHeader("X-Request-Id") String requestId
    ) {

        long startTime = System.currentTimeMillis();

        ResponseDTO response =
                service.processData(
                        request,
                        requestId,
                        startTime
                );

        return ResponseEntity.ok(response);
    }
}