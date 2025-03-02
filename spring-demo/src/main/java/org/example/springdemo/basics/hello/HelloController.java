package org.example.springdemo.basics.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/hello")
public class HelloController {
    private final HelloRepository repository;

    public HelloController(HelloRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String hello(Model model) {
        return "basics/hello";
    }

    @GetMapping("/list")
    public String helloForm(Model model) {
        model.addAttribute("hellos", repository.findAll());
        return "basics/hello";
    }

    @GetMapping("/{id}")
    public ModelAndView helloGet(@PathVariable Long id, Model model) {
        var hello = repository.findById(id);
        if (hello.isEmpty()) {
            return new ModelAndView("redirect:/hello/list");
        }
        var modelAndView = new ModelAndView("basics/hello");
        modelAndView.addObject("id", hello.get().getId());
        modelAndView.addObject("name", hello.get().getName());
        modelAndView.addObject("message", hello.get().getMessage());
        return modelAndView;
    }

    @PostMapping
    public String helloPost(@ModelAttribute("name") String name, @ModelAttribute("message") String message, Model model, @ModelAttribute("id") Long id) {
        var hello = new Hello();
        if (id == null || id == 0) {
            if (repository.findByAny(name, message) != null) {
                throw new IllegalArgumentException("Hello entry already exists");
            }
        } else {
            hello = repository.findById(id).orElseThrow();
        }
        hello.setName(name);
        hello.setMessage(message);
        repository.save(hello);
        return "redirect:/hello/list";
    }
}
