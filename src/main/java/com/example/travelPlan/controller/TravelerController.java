package com.example.travelPlan.controller;


import com.example.travelPlan.model.TravelPackage;
import com.example.travelPlan.model.Booking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/traveler")
public class TravelerController {
	
 
    @GetMapping("/home")
    public String showHomePage(Model model) {
        String username = "Traveler";  
        model.addAttribute("username", username);
        return "Traveler_Dashboard"; 
    }

    @GetMapping("/packages")
    public String showPackagesPage(Model model) {
        // Sample packages data
        List<TravelPackage> packages = Arrays.asList(
                new TravelPackage("Odisha Tour", "A 7-day tour of Odisha", 5000),
                new TravelPackage("Beaches of Odisha", "Relax at Odisha's best beaches", 3000)
        );
        model.addAttribute("packages", packages);
        return "traveler/packages";  // Render the packages page in the templates folder
    }


    
    @GetMapping("/my-bookings")
    public String showMyBookingsPage(Model model) {
       
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        model.addAttribute("bookings", bookings);
        return "my-bookings";  
    }

    
    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        model.addAttribute("name", "John Doe");
        model.addAttribute("email", "john.doe@example.com");
        return "profile"; 
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam String name, @RequestParam String email, Model model) {
        // Process the updated profile information here (e.g., save to database)
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "profile"; // Redirects back to the profile page
    }
    
    @GetMapping("/logout")
    public String logout() {
        
        return "redirect:/login";  
    }
}
