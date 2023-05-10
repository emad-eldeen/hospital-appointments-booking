package com.example.hospitalappointmentsbooking.businesslayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String patient;

    @NotBlank
    String doctor;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") // YYYY-MM-DD
    String date;
}

