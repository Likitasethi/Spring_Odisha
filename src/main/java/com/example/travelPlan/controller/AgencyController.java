package com.example.travelPlan.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.travelPlan.model.TravelPackage;
import com.example.travelPlan.repository.TravelPackageRepository;

@Controller
@RequestMapping("/agency")
public class AgencyController {

    private final TravelPackageRepository packageRepository;

    public AgencyController(TravelPackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("username", "Agency Name"); // Replace with actual username logic
        model.addAttribute("packages", packageRepository.findAll());
        return "agency_dashboard"; // Returns agency dashboard page directly
    }

    @GetMapping("/create-package")
    public String showCreatePackageForm(Model model) {
        model.addAttribute("travelPackage", new TravelPackage());
        return "create_package"; // Create package page
    }

    @PostMapping("/create-package")
    public String createPackage(@ModelAttribute TravelPackage travelPackage) {
        packageRepository.save(travelPackage);
        return "agency_dashboard"; // After saving, redirect back to dashboard
    }

    @GetMapping("/edit-package/{id}")
    public String showEditPackageForm(@PathVariable("id") Long id, Model model) {
        Optional<TravelPackage> packageOpt = packageRepository.findById(id);
        if (packageOpt.isPresent()) {
            model.addAttribute("travelPackage", packageOpt.get());
            return "edit_package"; // Edit package page
        }
        return "redirect:/agency/dashboard"; // Redirect to dashboard if package not found
    }

    @PostMapping("/edit-package/{id}")
    public String editPackage(@PathVariable("id") Long id, @ModelAttribute TravelPackage travelPackage) {
        travelPackage.setId(id); // Ensure the correct package is updated
        packageRepository.save(travelPackage);
        return "dashboard"; // Redirect back to dashboard after editing
    }

    @GetMapping("/delete-package/{id}")
    public String deletePackage(@PathVariable("id") Long id) {
        packageRepository.deleteById(id);
        return "redirect:/agency/dashboard"; // Redirect back to dashboard after deleting
    }
}
