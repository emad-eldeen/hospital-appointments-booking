package com.example.hospitalappointmentsbooking.presentation;

import com.example.hospitalappointmentsbooking.businesslayer.Booking;
import com.example.hospitalappointmentsbooking.businesslayer.Doctor;
import com.example.hospitalappointmentsbooking.businesslayer.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody @Valid Doctor doctor) {
        try {
            return doctorService.createDoctor(doctor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/bookings")
    public List<Booking> getBookings(@RequestParam String doc) {
        try {
            return doctorService.getBookings(doc);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}