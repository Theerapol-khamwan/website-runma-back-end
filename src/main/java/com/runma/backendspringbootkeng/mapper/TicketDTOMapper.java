package com.runma.backendspringbootkeng.mapper;

import com.runma.backendspringbootkeng.Model.TicketDTORequest;
import com.runma.backendspringbootkeng.Model.TicketDTOResponse;
import com.runma.backendspringbootkeng.entity.Ticket;
import org.springframework.stereotype.Service;


@Service
public class TicketDTOMapper {

    public TicketDTOResponse ticketResponse(Ticket ticket) {
        TicketDTOResponse ticketDTOResponse = new TicketDTOResponse();
        ticketDTOResponse.setId(ticket.getId());
        ticketDTOResponse.setCreateDate(ticket.getCreateDate());
        ticketDTOResponse.setStatus(ticket.getStatus());
        if (ticket.getRaceType() != null) {
            ticketDTOResponse.setRaceTypeName(ticket.getRaceType().getRace_name());
            ticketDTOResponse.setRaceTypePrize(ticket.getRaceType().getPrice());
        }

        if (ticket.getUserID() != null) {
            ticketDTOResponse.setUserId(ticket.getUserID().getId());
        }

        return ticketDTOResponse;
    }

    public Ticket ticketRequest(TicketDTORequest updateRequest, Ticket ticket) {

        ticket.setStatus(updateRequest.getStatus());
        ticket.setBankname(updateRequest.getBankName());
        ticket.setPaidDate(updateRequest.getPaidDate());
        ticket.setAmount(updateRequest.getAmount());
        ticket.setImageProof(updateRequest.getImageProof());

        return ticket;
    }


}

