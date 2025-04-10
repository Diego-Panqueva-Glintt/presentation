package com.report.presentation.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "squat_profile")
@Data
public class SquatProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String squat;
    private String profile;
    @Column(name = "num_profile")
    private Integer numProfile;
    @Column(name = "name_contact")
    private String nameContact;
    @Column(name = "full_time")
    private String fullTime;
    private String location;
    private String duration;
    private String company;
    @Column(name = "team_assign")
    private String teamAssign;
    @Column(name = "total_investment")
    private Float totalInvestment;
    @Column(name = "type_amount")
    private String typeAmount;
    @Column(name = "destination_q")
    private String destinationQ;
}
