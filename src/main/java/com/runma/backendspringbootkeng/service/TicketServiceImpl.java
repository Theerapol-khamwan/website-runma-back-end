package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.Model.TicketDTORequest;
import com.runma.backendspringbootkeng.Model.TicketDTOResponse;
import com.runma.backendspringbootkeng.entity.Event;
import com.runma.backendspringbootkeng.entity.Status;
import com.runma.backendspringbootkeng.entity.Ticket;
import com.runma.backendspringbootkeng.entity.User;
import com.runma.backendspringbootkeng.mapper.TicketDTOMapper;
import com.runma.backendspringbootkeng.repository.EventRepository;
import com.runma.backendspringbootkeng.repository.TicketRepository;
import com.runma.backendspringbootkeng.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketDTOMapper ticketDTOMapper;
    private final EventRepository eventRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, TicketDTOMapper ticketDTOMapper, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.ticketDTOMapper = ticketDTOMapper;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketDTOResponse findById(Integer theId) {
        return ticketRepository.findById(theId)
                .map((Function<Ticket, TicketDTOResponse>) ticketDTOMapper::ticketResponse)
                .orElseThrow(() -> new RuntimeException("Ticket with id [%s] not found".formatted(theId)));
    }


    @Override
    public void save(Ticket theTicket) {
        ticketRepository.save(theTicket);
    }

    @Override
    public void update(Integer ticketId, TicketDTORequest updateRequest) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket with id [%s] not found".formatted(ticketId)));

        ticket = ticketDTOMapper.ticketRequest(updateRequest, ticket);
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket findByIDTodelete(Integer theId) {

        Optional<Ticket> result = ticketRepository.findById(theId);

        Ticket theTicket = null;
        if (result.isPresent()) {
            theTicket = result.get();
        } else {
            throw new RuntimeException("Ticket with ID " + theId + " not found.");
        }

        return theTicket;
    }


    @Override
    public void deleteById(Integer theId) {
        ticketRepository.deleteById(theId);
    }

    @Override
    public List<Ticket> findByUserId(Integer userId) {
        Optional<User> resultUser = userRepository.findById(userId);
        if (!resultUser.isPresent()) {
            throw new RuntimeException("User not found with userId : " + userId);
        }

        List<Ticket> tickets = resultUser.get().getTicket();
        if (tickets.isEmpty()) {
            throw new RuntimeException("User has no tickets");
        }

        return tickets;
    }

    @Override
    public List<Ticket> querySaleReport(Integer eventID) {

        Optional<Event> resultEvent = eventRepository.findById(eventID);
        if (!resultEvent.isPresent()) {
            throw new RuntimeException("Event not found with eventId : " + eventID);
        }

        List<Ticket> resultTicket = ticketRepository.findByStatusAndRaceType_EventId(Status.unpaid, eventID);
        if (resultTicket.isEmpty()) {
            throw new RuntimeException("Event has no unpaid tickets");
        }

        return resultTicket;
    }


}
