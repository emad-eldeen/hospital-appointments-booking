package com.example.hospitalappointmentsbooking.businesslayer;

import com.example.hospitalappointmentsbooking.persistence.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        // change to lower case
        doctor.setDoctorName(doctor.getDoctorName().toLowerCase());
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor: doctorRepository.findAll()) {
            doctors.add(doctor);
        }
        return doctors;
    }

    public List<Booking> getBookings(String doctorName) {
        return doctorRepository.findByDoctorName(doctorName.toLowerCase())
                .orElseThrow().getBookings();
    }
}
