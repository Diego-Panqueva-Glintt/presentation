package com.report.presentation.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dual")
@Data
public class Dual {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dual;
}
