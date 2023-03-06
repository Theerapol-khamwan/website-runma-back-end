package com.runma.backendspringbootkeng.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "event")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date race_Date_time;

    @Temporal(TemporalType.DATE)
    private Date open_Regis_Date;

    @Temporal(TemporalType.DATE)
    private Date close_Regis_Date;

    private Boolean out_of_ticket_flag;

    private String province;

    private String location;

    private Integer capacity;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RaceType> raceTypeList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EVENT_ORGANIZER", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "organizer_id"))
    private List<Organizer> organizerList = new ArrayList<>();
    // initialize the list with an empty ArrayList

}

