package com.example.demo;

import com.example.demo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DemoApplicationTests {

    @Test
    void testPatientSettersAndGetters() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Alice");
        patient.setAge(25);
        patient.setSymptoms("fever");

        // Test key fields
        assertAll("Patient attributes",
                () -> assertEquals(1L, patient.getId()),
                () -> assertEquals("Alice", patient.getName()),
                () -> assertEquals(25, patient.getAge())
        );
    }

    @Test
    void testDoctorSettersAndGetters() {
        Doctor doctor = new Doctor();
        doctor.setId(101L);
        doctor.setName("Dr. Smith");

        // Test key fields
        assertAll("Doctor attributes",
                () -> assertEquals(101L, doctor.getId()),
                () -> assertTrue(doctor.getName().startsWith("Dr."))
        );
    }

    @Test
    void testAppointmentSettersAndGetters() {
        Appointment appointment = new Appointment();
        appointment.setId(500L);
        appointment.setSymptoms("cough");

        // Test key fields
        assertAll("Appointment attributes",
                () -> assertEquals(500L, appointment.getId()),
                () -> assertEquals("cough", appointment.getSymptoms())
        );
    }

    @Test
    void testPrescriptionSettersAndGetters() {
        Prescription prescription = new Prescription();
        prescription.setId(1L);
        prescription.setMedicationDetails("Take Paracetamol");

        // Test key fields
        assertAll("Prescription attributes",
                () -> assertEquals(1L, prescription.getId()),
                () -> assertTrue(prescription.getMedicationDetails().contains("Paracetamol"))
        );
    }
}
