package com.pratik.bfhl.service;

import com.pratik.bfhl.dto.RequestDTO;
import com.pratik.bfhl.dto.ResponseDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BFHLServiceImpl implements BFHLService {

    @Override
    public ResponseDTO processData(RequestDTO request) {

        List<String> odd = new ArrayList<>();
        List<String> even = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specials = new ArrayList<>();

        for(String item : request.getData()) {

            if(item.matches("\\d+")) {

                int num = Integer.parseInt(item);

                if(num % 2 == 0)
                    even.add(item);
                else
                    odd.add(item);
            }
            else if(item.matches("[a-zA-Z]+")) {

                alphabets.add(item.toUpperCase());
            }
            else {

                specials.add(item);
            }
        }

        return ResponseDTO.builder()
                .isSuccess(true)
                .oddNumbers(odd)
                .evenNumbers(even)
                .alphabets(alphabets)
                .specialCharacters(specials)
                .build();
    }
}