package com.report.presentation.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "price_total_day")
@Data
public class PriceTotalDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "month_price")
    private String monthPrice;
    @Column(name = "name_contact")
    private String nameContact;
    private String profile;
    @Column(name = "day_price")
    private Float dayPrice;
    @Column(name = "num_days")
    private Integer numDays;
    @Column(name = "q_year")
    private String qYear;

}
