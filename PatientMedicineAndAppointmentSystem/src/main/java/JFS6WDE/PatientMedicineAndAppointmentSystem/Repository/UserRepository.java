package JFS6WDE.PatientMedicineAndAppointmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}