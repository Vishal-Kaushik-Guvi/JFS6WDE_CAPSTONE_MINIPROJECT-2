package JFS6WDE.PatientMedicineAndAppointmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.AppointmentBooking;

public interface AppointmentRepository extends JpaRepository<AppointmentBooking,Long>{

}
