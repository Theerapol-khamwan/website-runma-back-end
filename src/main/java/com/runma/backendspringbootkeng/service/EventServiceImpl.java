package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.entity.Event;
import com.runma.backendspringbootkeng.entity.Organizer;
import com.runma.backendspringbootkeng.entity.RaceType;
import com.runma.backendspringbootkeng.repository.EventRepository;
import com.runma.backendspringbootkeng.repository.OrganizerRepository;
import com.runma.backendspringbootkeng.repository.RaceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final RaceTypeRepository raceTypeRepository;

    public EventServiceImpl(EventRepository eventRepository, OrganizerRepository organizerRepository, RaceTypeRepository raceTypeRepository) {
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.raceTypeRepository = raceTypeRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event theEvent, Integer organizerId) {
        Organizer organizer = organizerRepository.findById(organizerId).orElse(null);
        if (organizer == null) {
            throw new RuntimeException("Organizer not found");
        }

        theEvent.getOrganizerList().add(organizer);
        organizer.getEventList().add(theEvent);

        Event resultEvent = eventRepository.save(theEvent);

        if (theEvent.getRaceTypeList() != null) {
            for (RaceType raceType : theEvent.getRaceTypeList()) {
                raceType.setEvent(resultEvent);
                raceTypeRepository.save(raceType);
            }
        }

        return resultEvent;
    }


}
