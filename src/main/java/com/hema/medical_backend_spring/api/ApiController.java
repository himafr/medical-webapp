package com.hema.medical_backend_spring.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hema.medical_backend_spring.dto.DoctorApiDto;
import com.hema.medical_backend_spring.dto.ServiceResponseDto;
import com.hema.medical_backend_spring.model.Doctor.Specialty;
import com.hema.medical_backend_spring.services.AppointmentService;
import com.hema.medical_backend_spring.services.DoctorService;
import com.hema.medical_backend_spring.services.MedicalServiceService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final MedicalServiceService medicalServiceService;
    

 

    @GetMapping("/appointments/booked-dates")
    @ResponseBody
    public List<String> getBookedDates(@RequestParam Long doctorId) {
        return appointmentService.getFullyBookedDates(doctorId);
    }

    @GetMapping("/appointments/available-slots")
    @ResponseBody
    public List<String> getAvailableSlots(@RequestParam Long doctorId,
                                           @RequestParam String date) {
        return appointmentService.getBookedSlots(doctorId, LocalDate.parse(date));
    }
}
