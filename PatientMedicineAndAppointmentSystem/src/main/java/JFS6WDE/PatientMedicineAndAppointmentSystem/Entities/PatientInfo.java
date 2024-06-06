package JFS6WDE.PatientMedicineAndAppointmentSystem.Entities;

import java.util.List;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @Column(name = "Patient_Name")
    private String patientname;

    @Column(name = "Contact_Info")
    private String contactinfo;
    
    @Column(name = "Medical_History")
    private String medicalHistory;

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentBooking> appointmentBookings;

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User patientUser;

    public PatientInfo(String patientName, String contactinfo) {
        this.patientname = patientName;
        this.contactinfo = contactinfo;
    }
    
}
