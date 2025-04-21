package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.Prescription;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription generatePrescription(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        String symptoms = appointment.getSymptoms();
        String medication = switch (symptoms.toLowerCase()) {
            case "fever" -> "Take Paracetamol 500mg";
            case "cough" -> "Take Cough Syrup";
            case "headache" -> "Take Ibuprofen";
            case "cold" -> "Take Citrozine";
            default -> "Take General Multivitamins";
        };

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setMedicationDetails(medication);

        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }
}
