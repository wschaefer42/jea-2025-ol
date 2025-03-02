package org.example.springdemo.basics.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello-th")
public class HelloThController {
    private final HelloRepository repository;

    public HelloThController(HelloRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getHellos(Model model) {
        return "basics/hello_th";
    }

    @GetMapping("/list")
    public ModelAndView helloForm() {
        return new ModelAndView("basics/hello_th", "hellos", repository.findAll());
    }

    @GetMapping("/{id}/edit")
    public String helloGet(Model model, @PathVariable Long id) {
        var hello = repository.findById(id);
        hello.ifPresent(value -> model.addAttribute("hello", value));
        return "basics/hello_th";
    }

    @PostMapping
    public String helloPost(@ModelAttribute Hello hello) {
        repository.save(hello);
        return "redirect:/hello_th/list";
    }
}
