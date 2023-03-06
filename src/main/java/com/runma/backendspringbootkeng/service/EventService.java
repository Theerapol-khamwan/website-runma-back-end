package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.entity.Event;

import java.util.List;

public interface EventService {

    public List<Event> findAll();

    //    public void save(Event theEvent, Integer ogId);
    public Event save(Event theEvent, Integer organizerId);


}
