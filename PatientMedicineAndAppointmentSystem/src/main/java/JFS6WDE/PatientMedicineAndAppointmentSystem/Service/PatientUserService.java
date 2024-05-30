package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientRegistration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientUser;

public interface PatientUserService extends UserDetailsService {
 PatientUser save(PatientRegistration patientRegistration);
}
