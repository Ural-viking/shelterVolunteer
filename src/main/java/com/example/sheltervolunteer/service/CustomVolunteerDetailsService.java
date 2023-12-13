package com.example.sheltervolunteer.service;

import com.example.sheltervolunteer.entity.Volunteer;
import com.example.sheltervolunteer.repository.VolunteerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CustomVolunteerDetailsService implements UserDetailsService {

    private VolunteerRepository volunteerRepository;

    public CustomVolunteerDetailsService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Volunteer volunteer = volunteerRepository.findByEmail(usernameOrEmail);
        if(volunteer != null)   {
            return new org.springframework.security.core.userdetails.User(volunteer.getEmail(),
                    volunteer.getPassword(), volunteer.getRoles().stream().map((role) -> new SimpleGrantedAuthority(
                    role.getName())).collect(Collectors.toList()));
        }
        else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}