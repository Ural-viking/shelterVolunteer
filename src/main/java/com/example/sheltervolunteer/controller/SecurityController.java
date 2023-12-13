package com.example.sheltervolunteer.controller;

import com.example.shelterapp.dto.VolunteerDto;
import com.example.sheltervolunteer.entity.Volunteer;
import com.example.sheltervolunteer.service.VolunteerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

@Controller
public class SecurityController {
    private VolunteerService volunteerService;

    public SecurityController(VolunteerService volunteerService){
        this.volunteerService = volunteerService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        VolunteerDto volunteerDto = new VolunteerDto();
        model.addAttribute("volunteer", volunteerDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("volunteer") VolunteerDto volunteerDto, BindingResult result, Model model)	{
        Volunteer existingVolunteer = volunteerService.findVolunteerByEmail(volunteerDto.getEmail());
        if(existingVolunteer != null && existingVolunteer.getEmail() != null && !existingVolunteer.getEmail().isEmpty())	{
            result.rejectValue("email", null, "На этот адрес электронной почты уже зарегистрирована учетная запись");
        }
        if(result.hasErrors())	{
            model.addAttribute("volunteer", volunteerDto);
            return "/register";
        }
        volunteerService.saveVolunteer(volunteerDto);
        return "redirect:/register?success";
    }

    @GetMapping("/volonteers")
    public String volunteers(Model model)	{
        List<VolunteerDto> volunteers = volunteerService.findAllVolunteers();
        model.addAttribute("volunteers", volunteers);
        return "volunteer";
    }
}