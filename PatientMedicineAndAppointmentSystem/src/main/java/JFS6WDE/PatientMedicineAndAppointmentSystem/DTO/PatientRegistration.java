package JFS6WDE.PatientMedicineAndAppointmentSystem.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRegistration {

    private String patientname;
    
	private String email;

	private String contactinfo;

    private String password;

	public PatientRegistration(String patientname, String contactinfo, String email, String password) {
		super();
		this.patientname = patientname;
		this.contactinfo = contactinfo;
		this.email = email;
		this.password = password;
	}
    
}
