package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller

public class MainController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String listUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "userform";


    }

    @PostMapping("/process")
    public String processForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "userform";

        }
            userRepository.save(user);
            return "redirect:/";

    }

    @RequestMapping("/detail/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));
        return "userform";


    }

    @RequestMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));
        return "userform";

    }

    @RequestMapping("/delete/{id}")
    public String deluser(@PathVariable("id") long id) {
        userRepository.delete(id);
        return "redirect:/";
    }
}