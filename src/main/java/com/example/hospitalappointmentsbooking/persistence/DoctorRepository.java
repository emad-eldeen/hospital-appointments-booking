package com.example.hospitalappointmentsbooking.persistence;

import com.example.hospitalappointmentsbooking.businesslayer.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Optional<Doctor> findByDoctorName(String doctorName);
}
