package JFS6WDE.PatientMedicineAndAppointmentSystem.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByRole(String role);
}
