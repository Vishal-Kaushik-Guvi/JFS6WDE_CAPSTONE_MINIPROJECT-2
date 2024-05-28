package JFS6WDE.PatientMedicineAndAppointmentSystem.DTO;

import java.util.List;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.AppointmentBooking;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Medication;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Patient_Name", nullable = false)
    private String patientName;

    @Column(name = "Contact_Info", nullable = false)
    private String contactInfo;

    @Column(name = "Medical_Description", nullable = false)
    private String medicalDescription;

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentBooking> appointmentBookings;

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications;
    
    // login password
    private int password;
}
