package JFS6WDE.PatientMedicineAndAppointmentSystem.Entities;

import java.time.LocalDateTime;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentBooking {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Doctor_Name", nullable = false)
    private String doctorName;

    @Column(name = "Schedule", nullable = false)
    private LocalDateTime schedule;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientInfo patientInfo;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
