package JFS6WDE.PatientMedicineAndAppointmentSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

	@NotEmpty(message = "Patient Name Cannot be null")
    private String patientName;

    @Email
	private String email;

	@NotEmpty(message = "Phone no Cannot be null")
	private String contactInfo;

    @NotEmpty(message = "Password cannot be null")
    private String password;
    
}
