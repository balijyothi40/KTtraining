package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.model.Doctor;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String patientServiceUrl = "http://patient-service/api/patients/";
    private final String doctorServiceUrl = "http://doctor-service/api/doctors/";

    public Appointment createAppointment(Long patientId, Long doctorId, LocalDateTime appointmentDateTime) {
        Mono<Patient> patientMono = ((WebClient) webClientBuilder.baseUrl(patientServiceUrl))
                .get()
                .uri(patientId.toString())
                .retrieve()
                .bodyToMono(Patient.class);

        Mono<Doctor> doctorMono = ((WebClient) webClientBuilder.baseUrl(doctorServiceUrl))
                .get()
                .uri(doctorId.toString())
                .retrieve()
                .bodyToMono(Doctor.class);

        Appointment appointment = Mono.zip(patientMono, doctorMono)
                .map(tuple -> {
                    Patient patient = tuple.getT1();
                    Doctor doctor = tuple.getT2();

                    Appointment newAppointment = new Appointment();
                    newAppointment.setPatient(patient);
                    newAppointment.setDoctor(doctor);
                    newAppointment.setAppointmentDateTime(appointmentDateTime);
                    appointmentRepository.save(newAppointment);
                    return newAppointment;
                }).block();  
        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
