package com.report.presentation.service.csv.factory.impl;

import com.report.presentation.model.SquatProfile;
import com.report.presentation.repository.SquatProfileRepository;
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
public class CsvSquatProfileServiceImpl implements CsvService {

    private final SquatProfileRepository squatProfileRepository;

    @Autowired
    public CsvSquatProfileServiceImpl(SquatProfileRepository squatProfileRepository) {
        this.squatProfileRepository = squatProfileRepository;
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
                SquatProfile entry = new SquatProfile();
                entry.setSquat(record.get("squat"));
                entry.setProfile(record.get("perfil"));
                entry.setNumProfile(parseInteger(record.get("num_perfiles")));
                entry.setNameContact(record.get("contacto"));
                entry.setFullTime(record.get("dedicacion"));
                entry.setLocation(record.get("ubicacion"));
                entry.setDuration(record.get("duracion"));
                entry.setCompany(record.get("empresa"));
                entry.setTeamAssign(record.get("equipo_asignado"));
                entry.setTotalInvestment(parseFloat(record.get("total_inversion")));
                entry.setTypeAmount(record.get("tipo_moneda"));
                entry.setDestinationQ(record.get("destinacion_q"));
                squatProfileRepository.save(entry);
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
            squatProfileRepository.deleteAll();
        } catch (Exception e) {
            log.error("Error deleting all records from SquatProfile: {}", e.getMessage());
        }

    }
}
