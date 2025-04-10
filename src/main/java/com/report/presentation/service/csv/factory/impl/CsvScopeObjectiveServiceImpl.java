package com.report.presentation.service.csv.factory.impl;

import com.report.presentation.model.ScopeObjective;
import com.report.presentation.repository.ScopeObjectiveRepository;
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
public class CsvScopeObjectiveServiceImpl implements CsvService {

    private final ScopeObjectiveRepository scopeObjectiveRepository;

    @Autowired
    public CsvScopeObjectiveServiceImpl(ScopeObjectiveRepository scopeObjectiveRepository) {
        this.scopeObjectiveRepository = scopeObjectiveRepository;
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
                ScopeObjective entry = new ScopeObjective();
                entry.setProjectScope(record.get("alcance_descripcion"));
                entry.setProffesion(record.get("profesion_diferenciador_condiciones"));
                entry.setQTime(record.get("q_tiempo"));
                entry.setItemsCode(record.get("codigo_item"));

                scopeObjectiveRepository.save(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteAll() {
        try {
            scopeObjectiveRepository.deleteAll();
        } catch (Exception e) {
            log.error("Error deleting all records from scopeObjective: {}", e.getMessage());
        }

    }
}
