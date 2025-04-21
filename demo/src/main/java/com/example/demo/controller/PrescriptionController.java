package com.example.demo.controller;

import com.example.demo.model.Prescription;
import com.example.demo.service.PrescriptionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<Prescription> generatePrescription(@RequestParam Long appointmentId) {
    	Prescription prescription=prescriptionService.generatePrescription(appointmentId);
        return new ResponseEntity<>(prescription,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
    	List<Prescription> prescriptions=prescriptionService.getAllPrescriptions();
    	return new ResponseEntity<>(prescriptions,HttpStatus.OK);
        
    }
}
