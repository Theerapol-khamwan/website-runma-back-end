package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.Model.TicketDTORequest;
import com.runma.backendspringbootkeng.Model.TicketDTOResponse;
import com.runma.backendspringbootkeng.entity.Ticket;

import java.util.List;

public interface TicketService {

    public List<Ticket> findAll();

    public TicketDTOResponse findById(Integer theId);

    public void save(Ticket theTicket);

    public void update(Integer ticketId, TicketDTORequest updateRequest);

    public Ticket findByIDTodelete(Integer theId);

    public void deleteById(Integer theId);

    public List<Ticket> findByUserId(Integer userId);

    public List<Ticket> querySaleReport(Integer eventID);
}
