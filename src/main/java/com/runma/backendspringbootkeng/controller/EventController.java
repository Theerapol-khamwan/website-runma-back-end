package com.runma.backendspringbootkeng.controller;

import com.runma.backendspringbootkeng.entity.Event;
import com.runma.backendspringbootkeng.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @PostMapping("/{organizerId}")
    public Event create(@RequestBody Event theEven, @PathVariable Integer organizerId) {
        theEven.setId(0);

        eventService.save(theEven, organizerId);

        return theEven;
    }

    @PutMapping("/{eventId}")
    public Event update(@PathVariable Integer eventId, @RequestBody Event updateRequest) {
        return eventService.update(eventId, updateRequest);
    }


}
