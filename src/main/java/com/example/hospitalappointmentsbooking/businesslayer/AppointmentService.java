package com.example.hospitalappointmentsbooking.businesslayer;

import com.example.hospitalappointmentsbooking.persistence.AppointmentRepository;
import com.example.hospitalappointmentsbooking.persistence.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    public Appointment addAppointment(Appointment appointment) {
        // find the doctor by name. if not found, it throws an error
        Doctor doctor = doctorRepository.findByDoctorName(appointment.doctor).orElseThrow();
        // check if the doctor has an available booking for this appointment
        Booking matchingBooking = doctor.getBookings().stream()
                .filter(item -> !item.isBooked()) // filter available ones
                .filter(item -> item.getAvailableTime().toString().equals(appointment.date))
                // if any matches with the sent appointment
                .findFirst()
                .orElseThrow();
        matchingBooking.setBooked(true);
        return appointmentRepository.save(appointment);
    }

    public Appointment removeAppointment(long appointmentId) {
        Appointment removedAppointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointmentRepository.delete(removedAppointment);
        return removedAppointment;
    }

    public Collection<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment: appointmentRepository.findAll()) {
            appointments.add(appointment);
        }
        return appointments;
    }
}