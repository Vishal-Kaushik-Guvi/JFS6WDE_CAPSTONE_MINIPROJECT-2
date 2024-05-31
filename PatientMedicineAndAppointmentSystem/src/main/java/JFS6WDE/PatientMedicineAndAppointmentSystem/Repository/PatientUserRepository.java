package JFS6WDE.PatientMedicineAndAppointmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientUser;

public interface PatientUserRepository extends JpaRepository<PatientUser, Long> {
    PatientUser findByPatientname(String patientname);
}