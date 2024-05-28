package JFS6WDE.PatientMedicineAndAppointmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{

}
