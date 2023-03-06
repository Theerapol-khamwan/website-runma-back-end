package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.Model.TicketDTORequest;
import com.runma.backendspringbootkeng.Model.TicketDTOResponse;
import com.runma.backendspringbootkeng.entity.Ticket;
import com.runma.backendspringbootkeng.entity.User;
import com.runma.backendspringbootkeng.mapper.TicketDTORequestMapper;
import com.runma.backendspringbootkeng.mapper.TicketDTOResponseMapper;
import com.runma.backendspringbootkeng.repository.TicketRepository;
import com.runma.backendspringbootkeng.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketDTOResponseMapper ticketDTOResponseMapper;
    private final TicketDTORequestMapper ticketDTORequestMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, TicketDTOResponseMapper ticketDTOResponseMapper, TicketDTORequestMapper ticketDTORequestMapper) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.ticketDTOResponseMapper = ticketDTOResponseMapper;
        this.ticketDTORequestMapper = ticketDTORequestMapper;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketDTOResponse findById(Integer theId) {
        return ticketRepository.findById(theId)
                .map(ticketDTOResponseMapper)
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

        ticket = ticketDTORequestMapper.apply(updateRequest, ticket);
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
    public List<Ticket> findByUserId(int theId) {
        Optional<User> resultUser = userRepository.findById(theId);
        if (!resultUser.isPresent()) {
            throw new RuntimeException("User not found with userId : " + theId);
        }
        Integer userId = resultUser.get().getId();

        List<Ticket> theTicket = ticketRepository.findByUserID(userId);
        if (theTicket.isEmpty()) {
            throw new RuntimeException("Ticket not found with userId : " + theId);
        }

        return theTicket;
    }

}
