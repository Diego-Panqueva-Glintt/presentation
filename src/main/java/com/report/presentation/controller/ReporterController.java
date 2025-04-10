package com.report.presentation.controller;

import com.report.presentation.model.ParametersDto;
import com.report.presentation.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReporterController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/generate-pdf-report")
    public ResponseEntity<byte[]> generatePDFReport(@RequestParam("compania") String company,
                                                     @RequestParam("autor") String author,
                                                     @RequestParam("diasPago") String payDay,
                                                     @RequestParam("referenciaDocumento") String referenceDoc,
                                                     @RequestParam("referenciaDocVersion") String referenceDocVersion,
                                                    @RequestParam("titulo") String title) {
        ParametersDto parametersDto = new ParametersDto();
        parametersDto.setCompany(company);
        parametersDto.setAuthor(author);
        parametersDto.setPayDay(payDay);
        parametersDto.setReferenceDoc(referenceDoc);
        parametersDto.setReferenceDocVersion(referenceDocVersion);
        parametersDto.setTitle(title);
        try {
            byte[] pdfContent = reportService.generatePDFReport(parametersDto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "reporte.pdf");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
