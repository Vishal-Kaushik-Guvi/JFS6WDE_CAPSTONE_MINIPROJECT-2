package JFS6WDE.PatientMedicineAndAppointmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientInfo;

public interface PatientRepository extends JpaRepository<PatientInfo,Long> {

}
