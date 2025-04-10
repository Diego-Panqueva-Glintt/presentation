package com.report.presentation.controller;

import com.report.presentation.service.CsvLoaderComponent;
import com.report.presentation.service.csv.factory.CsvFactory;
import com.report.presentation.service.csv.factory.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CsvLoaderController {

    @Autowired
    private CsvLoaderComponent csvLoaderComponent;

    @GetMapping("/load-csv-all")
    public ResponseEntity<Map<String, Object>> loadCsvAll() throws IOException {
        csvLoaderComponent.loadCsv();
        return ResponseEntity.ok(Map.of("message", "CSV files loaded successfully"));
    }
}
