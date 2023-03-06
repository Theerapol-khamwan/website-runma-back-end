package com.runma.backendspringbootkeng.controller;

import com.runma.backendspringbootkeng.Model.TicketDTORequest;
import com.runma.backendspringbootkeng.Model.TicketDTOResponse;
import com.runma.backendspringbootkeng.entity.Ticket;
import com.runma.backendspringbootkeng.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTicketAll() {
        return ticketService.findAll();
    }

    @GetMapping("/{ticketId}")
    public TicketDTOResponse getTicket(@PathVariable Integer ticketId) {
        return ticketService.findById(ticketId);
    }


    @PostMapping
    public Ticket create(@RequestBody Ticket theTicket) {
        theTicket.setId(0);
        ticketService.save(theTicket);
        return theTicket;
    }

    @PutMapping("/{ticketId}")
    public String update(@PathVariable Integer ticketId, @RequestBody TicketDTORequest updateRequest) {

        ticketService.update(ticketId, updateRequest);

        return "Make PayMent ✅";
    }

    @DeleteMapping("/{ticketId}")
    public String deleteTicket(@PathVariable Integer ticketId) {

        Ticket tempTicket = ticketService.findByIDTodelete(ticketId);

        if (tempTicket == null) {
            throw new RuntimeException("Ticket id not found : " + ticketId);
        }

        ticketService.deleteById(ticketId);

        return "Deleted ticket id" + ticketId;
    }

    @GetMapping("/user/{userId}")
    public List<Ticket> searchByUser(@PathVariable Integer userId) {
        List<Ticket> theTicketByUser = ticketService.findByUserId(userId);

        if (theTicketByUser == null || theTicketByUser.isEmpty()) {
            throw new RuntimeException("Ticket is not found - " + userId);
        }

        return theTicketByUser;
    }

    @GetMapping("/query_sale_report/{eventId}")
    public List<Ticket> querySaleReport(@PathVariable Integer eventId) {
        return ticketService.querySaleReport(eventId);
    }


}
