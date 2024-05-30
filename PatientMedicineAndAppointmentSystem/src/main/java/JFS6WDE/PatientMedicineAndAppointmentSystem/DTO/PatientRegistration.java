package JFS6WDE.PatientMedicineAndAppointmentSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegistration {

    private String patientName;

    private String contactInfo;

    private String password;
}
