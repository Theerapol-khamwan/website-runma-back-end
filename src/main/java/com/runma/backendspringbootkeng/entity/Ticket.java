package com.runma.backendspringbootkeng.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paidDate;

    private String bankname;

    private Integer amount;

    private String imageProof;


    @ManyToOne
    private User userID;


    @ManyToOne
    private RaceType raceType;

}

