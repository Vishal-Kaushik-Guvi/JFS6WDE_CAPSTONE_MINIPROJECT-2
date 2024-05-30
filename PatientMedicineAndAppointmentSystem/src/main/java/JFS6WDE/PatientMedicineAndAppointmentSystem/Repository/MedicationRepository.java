package JFS6WDE.PatientMedicineAndAppointmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>{

}
