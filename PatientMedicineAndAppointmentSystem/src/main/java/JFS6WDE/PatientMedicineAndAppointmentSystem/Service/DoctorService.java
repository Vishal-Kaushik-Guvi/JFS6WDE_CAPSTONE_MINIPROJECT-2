package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Doctor;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    void deleteDoctor(Long id);
    Doctor getDoctorById(Long id);
    List<Doctor> getAllDoctors();
}
