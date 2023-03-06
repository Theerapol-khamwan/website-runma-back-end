package com.runma.backendspringbootkeng.mapper;

import com.runma.backendspringbootkeng.Model.TicketDTOResponse;
import com.runma.backendspringbootkeng.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TicketDTOResponseMapper implements Function<Ticket, TicketDTOResponse> {

    @Override
    public TicketDTOResponse apply(Ticket ticket) {

        TicketDTOResponse ticketDTOResponse = new TicketDTOResponse(
                ticket.getId(),
                ticket.getCreateDate(),
                ticket.getStatus(),
                ticket.getRaceType().getRace_name(),
                ticket.getRaceType().getPrice(),
                ticket.getUserID().getId()
        );
        return ticketDTOResponse;
    }

}

