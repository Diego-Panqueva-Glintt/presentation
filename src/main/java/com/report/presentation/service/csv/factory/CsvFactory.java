package com.report.presentation.service.csv.factory;

import com.report.presentation.service.csv.factory.impl.CsvObjectiveItemsServiceImpl;
import com.report.presentation.service.csv.factory.impl.CsvPriceTotalDayServiceImpl;
import com.report.presentation.service.csv.factory.impl.CsvScopeObjectiveServiceImpl;
import com.report.presentation.service.csv.factory.impl.CsvSquatProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvFactory {

    private final CsvObjectiveItemsServiceImpl csvObjectiveItemsService;
    private final CsvPriceTotalDayServiceImpl priceTotalDayService;
    private final CsvScopeObjectiveServiceImpl csvScopeObjectiveService;
    private final CsvSquatProfileServiceImpl csvSquatProfileService;

    @Autowired
    public CsvFactory(CsvObjectiveItemsServiceImpl csvObjectiveItemsService, CsvPriceTotalDayServiceImpl priceTotalDayService, CsvScopeObjectiveServiceImpl csvScopeObjectiveService, CsvSquatProfileServiceImpl csvSquatProfileService) {
        this.csvObjectiveItemsService = csvObjectiveItemsService;
        this.priceTotalDayService = priceTotalDayService;
        this.csvScopeObjectiveService = csvScopeObjectiveService;
        this.csvSquatProfileService = csvSquatProfileService;
    }

    public CsvService createCsvService(String fileCsv) {

        switch (fileCsv) {
            case "archivos_con_items.csv":
                return csvObjectiveItemsService;
            case "precios_valores.csv":
                return priceTotalDayService;
            case "detalle_resumen_ejecutivo.csv":
                return csvScopeObjectiveService;
            case "resumen_ejecutivo.csv":
                return csvSquatProfileService;
            default:
                throw new IllegalArgumentException("Unknown CSV type: " + fileCsv);
        }
    }
}
