package com.report.presentation.service.csv.factory.impl;

import com.report.presentation.model.ScopeObjectiveItems;
import com.report.presentation.repository.ScopeObjectiveItemsRepository;
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
public class CsvObjectiveItemsServiceImpl implements CsvService {

    private final ScopeObjectiveItemsRepository scopeObjectiveItemsRepository;

    @Autowired
    public CsvObjectiveItemsServiceImpl(ScopeObjectiveItemsRepository scopeObjectiveItemsRepository) {
        this.scopeObjectiveItemsRepository = scopeObjectiveItemsRepository;
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
                ScopeObjectiveItems entry = new ScopeObjectiveItems();
                entry.setCode(record.get("codigo_relacion"));
                entry.setDescription(record.get("detalle_descripcion"));

                scopeObjectiveItemsRepository.save(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteAll() {
        try {
            scopeObjectiveItemsRepository.deleteAll();
        } catch (Exception e) {
            log.error("Error deleting all records from scopeObjectiveItems: {}", e.getMessage());
        }

    }
}
