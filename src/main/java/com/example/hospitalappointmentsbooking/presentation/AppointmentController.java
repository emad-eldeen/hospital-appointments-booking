package com.example.hospitalappointmentsbooking.presentation;

import com.example.hospitalappointmentsbooking.businesslayer.Appointment;
import com.example.hospitalappointmentsbooking.businesslayer.AppointmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/appointments")
    public Appointment addAppointment(@RequestBody @Valid Appointment appointment) {
        try {
            return appointmentService.addAppointment(appointment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable @NotNull int id) {
        try {
            Appointment removedAppointment = appointmentService.removeAppointment(id);
            return new ResponseEntity<>(removedAppointment, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError =
                    new ApiError("The appointment does not exist or was already cancelled");
            return new ResponseEntity<>(
                    apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/appointments")
    public ResponseEntity<Object> getAppointments() {
        if (appointmentService.getAppointments().size() > 0) {
            return new ResponseEntity<>(appointmentService.getAppointments(), null, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
