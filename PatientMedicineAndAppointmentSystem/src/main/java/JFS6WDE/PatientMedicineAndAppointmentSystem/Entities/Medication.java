package JFS6WDE.PatientMedicineAndAppointmentSystem.Entities;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientInfo;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Medication_Name", nullable = false)
    private String name;

    @Column(name = "Dosage", nullable = false)
    private String dosage;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientInfo patientInfo;
}
