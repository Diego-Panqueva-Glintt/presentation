package com.report.presentation.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "scope_objective")
@Data
public class ScopeObjective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_scope")
    private String projectScope;

    private String proffesion;
    @Column(name = "q_time")
    private String qTime;
    @Column(name = "items_code")
    private String itemsCode;
}
