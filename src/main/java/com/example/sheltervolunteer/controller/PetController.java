package com.example.sheltervolunteer.controller;

import com.example.sheltervolunteer.entity.Pet;
import com.example.sheltervolunteer.repository.PetRepository;
import com.example.sheltervolunteer.service.VolunteerActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;


@Slf4j
@Controller
public class PetController {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private VolunteerActionService volunteerActionService;

    @GetMapping("/list")
    public ModelAndView getAllPets(){
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-pets");
        mav.addObject("pets",petRepository.findAll());
        volunteerActionService.savelog("Volunteer get all pets");
        return mav;
    }

    @GetMapping("/addPetForm")
    public ModelAndView addPetForm(){
        ModelAndView mav = new ModelAndView("add-pet-form");
        Pet pet = new Pet();
        mav.addObject("pet", pet);
        volunteerActionService.savelog("Volunteer add pet");
        return mav;
    }

    @RequestMapping(value = "/savePet", method = RequestMethod.GET)
    public String showForm(Pet pet){
        return "add-pet-form";
    }

    @RequestMapping(value = "/savePet", method = RequestMethod.POST)
    public String savePet(@Valid Pet pet, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add-pet-form";
        }
        else {
            petRepository.save(pet);
            volunteerActionService.savelog("Volunteer save pet");
            return "redirect:/list";
        }
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long petId){
        ModelAndView mav = new ModelAndView("add-pet-form");
        Optional<Pet> optionalPet = petRepository.findById(petId);
        Pet pet = new Pet();
        if(optionalPet.isPresent()){
            pet = optionalPet.get();
        }
        mav.addObject("pet", pet);
        volunteerActionService.savelog("Volunteer show update form");
        return mav;
    }

    @GetMapping("/deletePet")
    public String deletePet(@RequestParam Long petId){
        petRepository.deleteById(petId);
        volunteerActionService.savelog("volunteer delete pet");
        return "redirect:/list";
    }

  /*  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex){
        String error = "Данное поле должно содержать только цифры и не может быть пустым!";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } */

}