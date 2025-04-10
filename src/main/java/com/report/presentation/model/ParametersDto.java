package com.report.presentation.model;

public class ParametersDto {

    public ParametersDto(String company, String author, String payDay, String referenceDoc, String referenceDocVersion,
                         String title) {
        this.company = company;
        this.author = author;
        this.payDay = payDay;
        this.referenceDoc = referenceDoc;
        this.referenceDocVersion = referenceDocVersion;
        this.title = title;
    }

    public ParametersDto() {
    }

    private String company;
    private String author;
    private String payDay;
    private String referenceDoc;
    private String referenceDocVersion;
    private String title;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPayDay() {
        return payDay;
    }

    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }

    public String getReferenceDoc() {
        return referenceDoc;
    }

    public void setReferenceDoc(String referenceDoc) {
        this.referenceDoc = referenceDoc;
    }

    public String getReferenceDocVersion() {
        return referenceDocVersion;
    }

    public void setReferenceDocVersion(String referenceDocVersion) {
        this.referenceDocVersion = referenceDocVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
