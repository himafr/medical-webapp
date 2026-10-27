package com.hema.medical_backend_spring.controllers;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hema.medical_backend_spring.dto.AppointmentDto;
import com.hema.medical_backend_spring.model.Appointment;
import com.hema.medical_backend_spring.services.AppointmentService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("")
    public String getAppointments(Principal principal, @RequestParam(required = false) String specialty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, Model model) {
        Page<Appointment> appointmentsPage = appointmentService.getAppointmentsForPatient(principal.getName(), page,
                size);
        model.addAttribute("appointments", appointmentsPage.getContent());
        model.addAttribute("page", appointmentsPage.getNumber());
        model.addAttribute("number", appointmentsPage.getTotalPages());
        return new String("appointments");
    }

    @GetMapping("/book-appointment")
    public String getBookAppointment(@RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) Long serviceId,
            @RequestParam(required = false) String specialization,
            Model model) {
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("serviceId", serviceId);
        model.addAttribute("specialization", specialization);
        return new String("appointment");
    }

    @PostMapping("/book-appointment")
    public String bookAppointment(@ModelAttribute AppointmentDto dto, Principal principal) {
        appointmentService.createAppointment(dto, principal.getName());
        return "redirect:/home#appointments";
    }

    // المريض يحجز
    // @PostMapping
    // @PreAuthorize("hasRole('PATIENT')")
    // public ResponseEntity<AppointmentResponse> book(
    // @RequestBody @Valid AppointmentRequest request,
    // @AuthenticationPrincipal ProjectUser user) {
    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body(appointmentService.book(request, user));
    // }

    // // المريض يشوف حجوزاته
    // @GetMapping("/my")
    // @PreAuthorize("hasRole('PATIENT')")
    // public ResponseEntity<List<AppointmentResponse>> getMyAppointments(
    // @AuthenticationPrincipal ProjectUser user) {
    // return ResponseEntity.ok(appointmentService.getPatientAppointments(user));
    // }

    // // الدكتور يشوف مواعيده
    // @GetMapping("/doctor")
    // @PreAuthorize("hasRole('DOCTOR')")
    // public ResponseEntity<List<AppointmentResponse>> getDoctorAppointments(
    // @AuthenticationPrincipal ProjectUser user) {
    // return ResponseEntity.ok(appointmentService.getDoctorAppointments(user));
    // }

    // // الدكتور يأكد الحجز
    // @PatchMapping("/{id}/confirm")
    // @PreAuthorize("hasRole('DOCTOR')")
    // public ResponseEntity<Void> confirm(@PathVariable Long id,
    // @AuthenticationPrincipal ProjectUser user) {
    // appointmentService.confirm(id, user);
    // return ResponseEntity.ok().build();
    // }

    // // المريض أو الأدمن يلغي
    // @PatchMapping("/{id}/cancel")
    // @PreAuthorize("hasRole('PATIENT') or hasRole('ADMIN')")
    // public ResponseEntity<Void> cancel(@PathVariable Long id,
    // @AuthenticationPrincipal ProjectUser user) {
    // appointmentService.cancel(id, user);
    // return ResponseEntity.ok().build();
    // }

}
