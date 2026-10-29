package com.hema.medical_backend_spring.api;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.dto.ApiResponse;
import com.hema.medical_backend_spring.dto.AppointmentDto;
import com.hema.medical_backend_spring.model.Appointment;
import com.hema.medical_backend_spring.services.AppointmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/appointments")
@AllArgsConstructor
public class AppointmentApi {
    private final AppointmentService appointmentService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAppointments(Principal principal, @RequestParam(required = false) String specialty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Appointment> appointmentsPage = appointmentService.getAppointmentsForPatient(principal.getName(), page,
                size);
       return ResponseEntity.ok(ApiResponse.success("",appointmentsPage));
    }

    @GetMapping("/booked-dates")
    public ResponseEntity<ApiResponse> getBookedDates(@RequestParam Long doctorId) {
        return ResponseEntity.ok(ApiResponse.success("booked dates",appointmentService.getFullyBookedDates(doctorId)));
    }
    @GetMapping("/available-slots")
    
    public ResponseEntity<ApiResponse> getAvailableSlots(@RequestParam Long doctorId,
                                           @RequestParam String date) {
        return ResponseEntity.ok(ApiResponse.success("available slots",appointmentService.getBookedSlots(doctorId, LocalDate.parse(date))));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> bookAppointment(@Valid @RequestBody  AppointmentDto dto, Principal principal) {
        appointmentService.createAppointment(dto, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Appointment booked successfully"));
    }

    // المريض يحجز
    // @PostMapping
    // @PreAuthorize("hasRole('PATIENT')")
    // public ResponseEntity<ApiResponse> book(
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
