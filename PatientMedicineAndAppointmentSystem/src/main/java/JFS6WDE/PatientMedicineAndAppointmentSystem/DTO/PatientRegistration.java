package JFS6WDE.PatientMedicineAndAppointmentSystem.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRegistration {

    private String patientname;

    private String contactInfo;

    private String password;

	public PatientRegistration(String patientname, String contactInfo, String password) {
		super();
		this.patientname = patientname;
		this.contactInfo = contactInfo;
		this.password = password;
	}
    
}
