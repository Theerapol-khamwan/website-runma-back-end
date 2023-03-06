package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.entity.Ticket;
import com.runma.backendspringbootkeng.entity.User;
import com.runma.backendspringbootkeng.repository.TicketRepository;
import com.runma.backendspringbootkeng.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public UserServiceImpl(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public User findById(Integer userId) {
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("User with ID " + userId + " not found.");
        }

        return theUser;
    }

    @Override
    public User save(User theUser) {

        User resultUser = userRepository.save(theUser);

        if (theUser.getTicket() != null) {
            for (Ticket ticket : resultUser.getTicket()) {
                ticket.setUserID(resultUser);
                ticketRepository.save(ticket);
            }
        }

        return resultUser;
    }

    @Override
    public User update(Integer userId, User updateRequest) {

        if (updateRequest == null) {
            throw new RuntimeException("Update request cannot be null");
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User theUser = user.get();
            theUser.setPassword(updateRequest.getPassword());
            theUser.setFName(updateRequest.getFName());
            theUser.setLName(updateRequest.getLName());
            theUser.setEmail(updateRequest.getEmail());
            theUser.setPhone(updateRequest.getPhone());
            theUser.setGender(updateRequest.getGender());
            theUser.setBirthDate(updateRequest.getBirthDate());
            theUser.setAddress(updateRequest.getAddress());
            theUser.setCountry(updateRequest.getCountry());
            theUser.setProvince(updateRequest.getProvince());
            theUser.setSubDistrict(updateRequest.getSubDistrict());
            theUser.setPostalCode(updateRequest.getPostalCode());

            List<Ticket> tickets = updateRequest.getTicket();
            if (tickets != null && !tickets.isEmpty()) {
                for (Ticket ticket : tickets) {
                    ticket.setUserID(theUser);
                    ticketRepository.save(ticket);
                }
                theUser.setTicket(tickets);
            }
            return userRepository.save(theUser);
        } else {
            throw new RuntimeException("Event with ID " + userId + " not found.");
        }

    }

    @Override
    public User findByEmail(String userEmail) {
        if (userEmail == null || userEmail.isEmpty()) {
            throw new RuntimeException("Email address cannot be null or empty.");
        }
        return userRepository.findByEmail(userEmail);
    }
}
