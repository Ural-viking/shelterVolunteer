package com.example.sheltervolunteer.service;

import com.example.shelterapp.dto.VolunteerDto;
import com.example.sheltervolunteer.entity.Volunteer;

import java.util.List;

public interface VolunteerService {
    void saveVolunteer(VolunteerDto volunteerDto);
    Volunteer findVolunteerByEmail(String email);
    List<VolunteerDto> findAllVolunteers();
}