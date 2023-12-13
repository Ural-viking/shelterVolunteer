package com.example.sheltervolunteer.repository;


import com.example.sheltervolunteer.entity.VolunteerAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerActionRepository extends JpaRepository<VolunteerAction, Long> {
}