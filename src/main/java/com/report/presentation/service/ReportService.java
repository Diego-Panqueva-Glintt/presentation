package com.report.presentation.service;

import com.report.presentation.model.ParametersDto;

public interface ReportService {

    public byte[] generatePDFReport(ParametersDto parametersDto);
}
