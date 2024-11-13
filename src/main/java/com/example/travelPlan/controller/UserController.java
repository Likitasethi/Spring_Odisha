package com.example.travelPlan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelPlan.model.User;
import com.example.travelPlan.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

   
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private static final String AGENCY_USERNAME = "agency";
    private static final String AGENCY_PASSWORD = "agency123";

    @GetMapping("/")
    public String home() {
        return "home"; 
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration"; 
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/login"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            
            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                return "redirect:/admin_dashboard"; 
            }
            
            else if (AGENCY_USERNAME.equals(username) && AGENCY_PASSWORD.equals(password)) {
                return "redirect:/agency_dashboard"; 
            }
            
            else {
                return "redirect:/traveler_dashboard?username=" + username; 
            }
        }
        
        return "login"; 
    }

    @GetMapping("/traveler_dashboard")
    public String showTravelerDashboard(@RequestParam String username, Model model) {
        model.addAttribute("username", username); 
        return "traveler_dashboard"; 
    }

    @GetMapping("/admin_dashboard")
    public String showAdminDashboard(Model model) {
        return "admin_dashboard"; 
    }

    @GetMapping("/agency_dashboard")
    public String showAgencyDashboard(Model model) {
        return "agency_dashboard";
    }

    @GetMapping("/logout")
    public String handleLogout() {
        return "login"; 
    }
}