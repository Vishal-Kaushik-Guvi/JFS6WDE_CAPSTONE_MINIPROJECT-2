package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Registration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.User;

public interface UserService extends UserDetailsService {
	
 User save(Registration patientRegistration);

 User findUserByEmail(String email);

 List<Registration> findAllUser();
}
