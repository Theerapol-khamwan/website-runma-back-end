package com.runma.backendspringbootkeng.Model;

import com.runma.backendspringbootkeng.entity.Status;

import java.util.Date;

public record TicketDTORequest(
        Status status,
        String bankName,
        Date paidDate,
        Integer amount,
        String imageProof
) {
}
