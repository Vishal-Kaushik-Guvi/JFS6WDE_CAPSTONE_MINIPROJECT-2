package JFS6WDE.PatientMedicineAndAppointmentSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRegistration {
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private String firstName;
    private  String lastName;
    private String email;
    private  String password;
}
}
