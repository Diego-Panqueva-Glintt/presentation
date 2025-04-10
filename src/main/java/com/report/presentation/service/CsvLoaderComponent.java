package com.report.presentation.service;

import com.report.presentation.service.csv.factory.CsvFactory;
import com.report.presentation.service.csv.factory.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class CsvLoaderComponent {

    @Autowired
    private CsvFactory csvFactory;

    public void loadCsv() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:static/csv/*.csv");

        Arrays.stream(resources).forEach(csv ->{
            CsvService csvService = csvFactory.createCsvService(csv.getFilename());
            csvService.saveCsv(csv);
        });


    }
}

