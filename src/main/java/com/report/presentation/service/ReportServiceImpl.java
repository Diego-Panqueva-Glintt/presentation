package com.report.presentation.service;

import com.report.presentation.model.ParametersDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final DataSource dataSource;
    private final String templatePath = "principal.jrxml";

    @Autowired
    private ResourceLoader resourceLoader;

    public ReportServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(readOnly = true)
    @Override
    public byte[] generatePDFReport(ParametersDto parametersDto) {
        return myReport(parametersDto);
    }

    private byte[] reportPDF() {
        InputStream reportStream = this.getClass().getResourceAsStream("/templates/" + "principal.jasper");

        Connection connection = null;
        JasperPrint jasperPrint = null;
        try {
            connection = dataSource.getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("P_COMPANY", "MásMóvil");
            parameters.put("P_TITLE_DUMMY", "MasOrange");
            parameters.put("P_AUTOR", "Juan Anguita");
            parameters.put("REPORT_CONNECTION", connection);
            jasperPrint = JasperFillManager.fillReport(
                    reportStream,
                    parameters, // Parámetros (vacío si no hay parámetros)
                    connection
            );
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] myReport(ParametersDto parametersDto) {
        try {
            JasperReport principal = compileReport("principal");

            Connection connection = null;
            connection = dataSource.getConnection();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("P_COMPANY", parametersDto.getCompany());
            parameters.put("P_TITLE_DUMMY", parametersDto.getTitle());
            parameters.put("P_AUTOR", parametersDto.getAuthor());
            parameters.put("P_DAYS_PAY", parametersDto.getPayDay());
            parameters.put("P_REF_DOC", parametersDto.getReferenceDoc());
            parameters.put("P_REF_DOC_VERSION", parametersDto.getReferenceDocVersion());
            parameters.put("REPORT_CONNECTION", connection);
            parameters.put("SUBREPORT_DIR", resourceLoader.getResource("classpath:templates/").getFile().getAbsolutePath() + File.separator);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    principal,
                    parameters,
                    connection
            );

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private JasperReport compileReport(String name) throws IOException, JRException {
        InputStream input = resourceLoader.getResource("classpath:templates/" + name + ".jrxml").getInputStream();
        JasperDesign design = JRXmlLoader.load(input);
        return JasperCompileManager.compileReport(design);
    }


}
