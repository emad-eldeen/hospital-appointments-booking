package com.example.hospitalappointmentsbooking.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private long id;

    @NotBlank
    @Column(name = "doctor_name", unique = true)
    private String doctorName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL) // mappedBy is not needed since it is a unidirectional association
    @JoinColumn
    private List<Booking> bookings = new ArrayList<>();


    public Doctor() {
        // create four available dates by default
        for (int i = 1; i < 5; i++) {
            LocalDate localDate = LocalDate.now().plus(Period.ofDays(i));
            Booking booking = new Booking();
            booking.setAvailableTime(localDate);
            bookings.add(booking);
        }
    }
}