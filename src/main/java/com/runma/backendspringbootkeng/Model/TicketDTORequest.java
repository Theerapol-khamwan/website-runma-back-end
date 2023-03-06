package com.runma.backendspringbootkeng.Model;

import com.runma.backendspringbootkeng.entity.Status;
import lombok.Data;

import java.util.Date;

@Data
public class TicketDTORequest {

    private Status status;

    private String bankName;

    private Date paidDate;

    private Integer amount;

    private String imageProof;
}
