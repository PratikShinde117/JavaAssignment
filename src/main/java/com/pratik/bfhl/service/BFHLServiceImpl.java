package com.pratik.bfhl.service;

import com.pratik.bfhl.dto.RequestDTO;
import com.pratik.bfhl.dto.ResponseDTO;
import com.pratik.bfhl.dto.SummaryDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BFHLServiceImpl implements BFHLService {

    @Override
    public ResponseDTO processData(
            RequestDTO request,
            String requestId,
            long startTime
    ) {

        Set<String> unique = new HashSet<>();

        boolean containsDuplicates = false;

        int invalidElements = 0;

        String longestAlphabeticValue = null;
        String shortestAlphabeticValue = null;

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();

        List<BigDecimal> numbers = new ArrayList<>();

        Map<String, Integer> alphabetFrequency =
                new HashMap<>();

        int vowelCount = 0;

        Pattern numberPattern =
                Pattern.compile("^-?\\d+(\\.\\d+)?$");

        Pattern alphanumericPattern =
                Pattern.compile("[A-Za-z]+|\\d+");

        for (String item : request.getData()) {

            if (item == null) {
                invalidElements++;
                continue;
            }

            item = item.trim();

            if (item.isEmpty()) {
                invalidElements++;
                continue;
            }

            if (!unique.add(item)) {
                containsDuplicates = true;
                continue;
            }

            // Pure Number
            if (numberPattern.matcher(item).matches()) {

                BigDecimal num =
                        new BigDecimal(item);

                numbers.add(num);

                if (num.scale() == 0) {

                    if (num.remainder(
                            BigDecimal.valueOf(2))
                            .compareTo(BigDecimal.ZERO)
                            == 0) {

                        evenNumbers.add(
                                num.toPlainString()
                        );
                    } else {

                        oddNumbers.add(
                                num.toPlainString()
                        );
                    }
                }

                continue;
            }

            // Pure Alphabet
            if (item.matches("[A-Za-z]+")) {

                String upper =
                        item.toUpperCase();

                alphabets.add(upper);

                if (longestAlphabeticValue == null
                        || upper.length() >
                        longestAlphabeticValue.length()) {

                    longestAlphabeticValue = upper;
                }

                if (shortestAlphabeticValue == null
                        || upper.length() <
                        shortestAlphabeticValue.length()) {

                    shortestAlphabeticValue = upper;
                }

                for (char ch :
                        upper.toCharArray()) {

                    String letter =
                            String.valueOf(ch);

                    alphabetFrequency.put(
                            letter,
                            alphabetFrequency
                                    .getOrDefault(
                                            letter,
                                            0
                                    ) + 1
                    );

                    if ("AEIOU".contains(letter)) {
                        vowelCount++;
                    }
                }

                continue;
            }

            // Alphanumeric
            if (item.matches("[A-Za-z0-9]+")) {

                Matcher matcher =
                        alphanumericPattern
                                .matcher(item);

                while (matcher.find()) {

                    String token =
                            matcher.group();

                    if (token.matches("\\d+")) {

                        numbers.add(
                                new BigDecimal(token)
                        );
                    } else {

                        String upper =
                                token.toUpperCase();

                        alphabets.add(upper);

                        if (longestAlphabeticValue == null
                                || upper.length() >
                                longestAlphabeticValue.length()) {

                            longestAlphabeticValue = upper;
                        }

                        if (shortestAlphabeticValue == null
                                || upper.length() <
                                shortestAlphabeticValue.length()) {

                            shortestAlphabeticValue = upper;
                        }

                        for (char ch :
                                upper.toCharArray()) {

                            String letter =
                                    String.valueOf(ch);

                            alphabetFrequency.put(
                                    letter,
                                    alphabetFrequency
                                            .getOrDefault(
                                                    letter,
                                                    0
                                            ) + 1
                            );

                            if ("AEIOU".contains(letter)) {
                                vowelCount++;
                            }
                        }
                    }
                }

                continue;
            }

            // Special Character
            specialCharacters.add(item);
        }

        BigDecimal sum = BigDecimal.ZERO;

        BigDecimal largest = null;
        BigDecimal smallest = null;

        for (BigDecimal num : numbers) {

            sum = sum.add(num);

            if (largest == null
                    || num.compareTo(largest) > 0) {

                largest = num;
            }

            if (smallest == null
                    || num.compareTo(smallest) < 0) {

                smallest = num;
            }
        }

        Collections.sort(numbers);

        List<String> sortedNumbers =
                numbers.stream()
                        .map(BigDecimal::toPlainString)
                        .toList();

        int alphabetCount =
                alphabetFrequency.values()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum();

        int validProcessed =
                request.getData().size()
                        - invalidElements;

        SummaryDTO summary =
                new SummaryDTO(
                        request.getData().size(),
                        validProcessed,
                        invalidElements
                );

        long processingTime =
                System.currentTimeMillis()
                        - startTime;

        return ResponseDTO.builder()
                .isSuccess(true)

                .requestId(requestId)

                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)

                .alphabets(alphabets)
                .specialCharacters(specialCharacters)

                .sum(sum.toPlainString())

                .largestNumber(
                        largest == null
                                ? null
                                : largest.toPlainString()
                )

                .smallestNumber(
                        smallest == null
                                ? null
                                : smallest.toPlainString()
                )

                .alphabetCount(alphabetCount)

                .numberCount(numbers.size())

                .specialCharacterCount(
                        specialCharacters.size()
                )

                .containsDuplicates(
                        containsDuplicates
                )

                .vowelCount(vowelCount)

                .alphabetFrequency(
                        alphabetFrequency
                )

                .uniqueElementCount(
                        unique.size()
                )

                .sortedNumbers(
                        sortedNumbers
                )

                .longestAlphabeticValue(
                        longestAlphabeticValue
                )

                .shortestAlphabeticValue(
                        shortestAlphabeticValue
                )

                .summary(summary)

                .processingTimeMs(
                        processingTime
                )

                .build();
    }
}