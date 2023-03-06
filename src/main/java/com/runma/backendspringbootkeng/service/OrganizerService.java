package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.entity.Organizer;

import java.util.List;

public interface OrganizerService {

    public List<Organizer> findAll();

    public void save(Organizer theOrganizer);

}
