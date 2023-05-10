package com.example.hospitalappointmentsbooking.persistence;

import com.example.hospitalappointmentsbooking.businesslayer.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
