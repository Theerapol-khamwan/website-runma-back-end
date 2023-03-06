package com.runma.backendspringbootkeng.repository;

import com.runma.backendspringbootkeng.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
