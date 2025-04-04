package com.example.springgettingstarted.db;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("users", userRepository.findAll());
    }


    @GetMapping
    public String listAllUsers(Model model) {
        return "users";
    }

    @GetMapping("/{id}")
    public ModelAndView listUsers(@PathVariable("id") Long id) {
        var user = userRepository.findById(id).orElse(null);
        var mav = new ModelAndView("users");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users/";
    }

}
