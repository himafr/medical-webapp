package com.hema.medical_backend_spring.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hema.medical_backend_spring.dto.AppointmentDto;
import com.hema.medical_backend_spring.exception.NotValidTimeException;
import com.hema.medical_backend_spring.exception.UserNotFoundException;
import com.hema.medical_backend_spring.model.Appointment;
import com.hema.medical_backend_spring.model.Doctor;
import com.hema.medical_backend_spring.model.MedicalServiceEntity;
import com.hema.medical_backend_spring.model.Patient;
import com.hema.medical_backend_spring.repository.AppointmentRepository;
import com.hema.medical_backend_spring.repository.DoctorRepository;
import com.hema.medical_backend_spring.repository.MedicalServiceEntityRepository;
import com.hema.medical_backend_spring.repository.PatientRepository;
import com.hema.medical_backend_spring.validation.AppointmentValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService {
        private final AppointmentRepository appointmentRepository;
        private final DoctorRepository doctorRepository;
        private final MedicalServiceEntityRepository medicalServiceRepository;
        private final PatientRepository patientRepository;

        public Page<Appointment> getAppointmentsForPatient(String patientEmail, int page, int size) {
                Pageable pageable = PageRequest.of(page, size);

                Patient patient = patientRepository.findByEmail(patientEmail)
                                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
                return appointmentRepository.findByPatient(patient, pageable);
        }

        public void createAppointment(AppointmentDto dto, String patientEmail) {
                // TODO CHECK DATE TOO
                if (!AppointmentValidator.isTimeAllowed(dto.getAppointmentTime())) {
                        throw new NotValidTimeException("هذا الوقت غير مسموح به");
                }
                Patient patient = patientRepository.findByEmail(patientEmail)
                                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
                @SuppressWarnings("null")
                Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));
                @SuppressWarnings("null")
                MedicalServiceEntity service = medicalServiceRepository.findById(dto.getServiceId())
                                .orElseThrow(() -> new UserNotFoundException("Service not found"));

                boolean slotTaken = AppointmentValidator.isSlotTaken(appointmentRepository, dto);
                if (slotTaken)
                        throw new RuntimeException("هذا الموعد محجوز بالفعل");

                Appointment appointment = new Appointment();
                appointment.setPatient(patient);
                appointment.setDoctor(doctor);
                appointment.setMedicalService(service);
                appointment.setAppointmentDate(dto.getAppointmentDate());
                appointment.setAppointmentTime(dto.getAppointmentTime());
                appointment.setNotes(dto.getNotes());
                appointment.setStatus(Appointment.AppointmentStatus.PENDING);

                appointmentRepository.save(appointment);
        }

        public List<String> getFullyBookedDates(Long doctorId) {
                List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
                Map<LocalDate, Long> countPerDay = appointments.stream()
                                .collect(Collectors.groupingBy(Appointment::getAppointmentDate, Collectors.counting()));
                return countPerDay.entrySet().stream()
                                .filter(e -> e.getValue() >= 4)
                                .map(e -> e.getKey().toString())
                                .collect(Collectors.toList());
        }

        public List<String> getBookedSlots(Long doctorId, LocalDate date) {
                return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date)
                                .stream()
                                .map(a -> a.getAppointmentTime().toString())
                                .collect(Collectors.toList());
        }
}