package com.report.presentation.service.csv.factory.impl;

import com.report.presentation.model.PriceTotalDay;
import com.report.presentation.repository.PriceTotalDayRepository;
import com.report.presentation.service.csv.factory.CsvService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class CsvPriceTotalDayServiceImpl implements CsvService {

    private final PriceTotalDayRepository priceTotalDayRepository;

    @Autowired
    public CsvPriceTotalDayServiceImpl(PriceTotalDayRepository priceTotalDayRepository) {
        this.priceTotalDayRepository = priceTotalDayRepository;
    }

    @Override
    public void saveCsv(Resource resource) {
        deleteAll();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
        )) {

            CSVFormat format = CSVFormat.Builder.create()
                    .setDelimiter(';')
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setIgnoreEmptyLines(true)
                    .setTrim(true)
                    .build();

            CSVParser records = format.parse(reader);

            for (CSVRecord record : records) {
                PriceTotalDay entry = new PriceTotalDay();
                entry.setMonthPrice(record.get("mes").toUpperCase());
                entry.setNameContact(record.get("nombre"));
                entry.setProfile(record.get("perfil"));
                entry.setDayPrice(parseFloat(record.get("precio_dia")));
                entry.setNumDays(parseInteger(record.get("num_dias")));
                entry.setQYear(record.get("q_asignado"));
                priceTotalDayRepository.save(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Float parseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void deleteAll() {
        try {
            priceTotalDayRepository.deleteAll();
        } catch (Exception e) {
            log.error("Error deleting all records from priceTotalDay: {}", e.getMessage());
        }
    }
}
