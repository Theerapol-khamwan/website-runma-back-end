package com.runma.backendspringbootkeng.controller;

import com.runma.backendspringbootkeng.entity.Organizer;
import com.runma.backendspringbootkeng.service.OrganizerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizer")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public List<Organizer> findAll() {
        return organizerService.findAll();
    }

    @PostMapping
    public Organizer createOrganizer(@RequestBody Organizer theOrganizer) {
        theOrganizer.setId(0);

        organizerService.save(theOrganizer);

        return theOrganizer;
    }

}
