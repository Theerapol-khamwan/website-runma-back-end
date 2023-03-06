package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.entity.Organizer;
import com.runma.backendspringbootkeng.repository.OrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;

    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Override
    public List<Organizer> findAll() {
        return organizerRepository.findAll();
    }

    @Override
    public void save(Organizer theOrganizer) {
        organizerRepository.save(theOrganizer);
    }
}
