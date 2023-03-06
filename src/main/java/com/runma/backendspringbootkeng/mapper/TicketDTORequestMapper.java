package com.runma.backendspringbootkeng.mapper;

import com.runma.backendspringbootkeng.Model.TicketDTORequest;
import com.runma.backendspringbootkeng.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class TicketDTORequestMapper implements BiFunction<TicketDTORequest, Ticket, Ticket> {

    @Override
    public Ticket apply(TicketDTORequest updateRequest, Ticket ticket) {

        ticket.setStatus(updateRequest.status());
        ticket.setBankname(updateRequest.bankName());
        ticket.setPaidDate(updateRequest.paidDate());
        ticket.setAmount(updateRequest.amount());
        ticket.setImageProof(updateRequest.imageProof());

        return ticket;
    }
}
