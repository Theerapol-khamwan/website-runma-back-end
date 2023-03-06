package com.runma.backendspringbootkeng.Model;

import com.runma.backendspringbootkeng.entity.Status;
import lombok.Data;

import java.util.Date;

@Data
public class TicketDTOResponse {

    private Integer id;
    private Date createDate;
    private Status status;
    private String raceTypeName;
    private Double raceTypePrize;
    private Integer userId;

}
