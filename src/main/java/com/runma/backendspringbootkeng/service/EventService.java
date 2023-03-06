package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.entity.Event;

import java.util.List;

public interface EventService {

    public List<Event> findAll();

    public Event save(Event theEvent, Integer organizerId);

    public Event update(Integer eventId, Event updateRequest);

}
