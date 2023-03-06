package com.runma.backendspringbootkeng.repository;

import com.runma.backendspringbootkeng.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUserID(Integer userID);
}
