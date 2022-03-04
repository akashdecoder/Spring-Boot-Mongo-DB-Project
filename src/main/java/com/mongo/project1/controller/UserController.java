package com.mongo.project1.controller;

import com.mongo.project1.model.User;
import com.mongo.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String addUser(@Valid User user, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
        Random random = new Random();
        user.setId(Integer.toString(random.nextInt(1000000)));
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Registration Successful");
        return "redirect:/register";
    }
}
