package com.example.springclassroom.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/persons")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("persons", personRepository.findAll());
    }

    @GetMapping
    public String persons(Model model) {
        return "mvc/persons";
    }

    @GetMapping("/{id}")
    public ModelAndView person(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("mvc/persons");
        mav.addObject("person", personRepository.findById(id).orElse(null));
        return mav;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("person") Person person) {
        personRepository.save(person);
        return "redirect:/persons";
    }

}
