
package com.example.travelPlan.repository;

import com.example.travelPlan.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
}
