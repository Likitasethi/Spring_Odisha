package com.example.travelPlan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.travelPlan.model.Booking;
import com.example.travelPlan.repository.BookingRepository;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    // Assuming the user ID is stored in the session during login
    @GetMapping("/my-bookings")
    public String myBookings(@SessionAttribute("userId") Long userId, Model model) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        model.addAttribute("bookings", bookings);
        return "my-bookings"; // Thymeleaf template name
    }
}
