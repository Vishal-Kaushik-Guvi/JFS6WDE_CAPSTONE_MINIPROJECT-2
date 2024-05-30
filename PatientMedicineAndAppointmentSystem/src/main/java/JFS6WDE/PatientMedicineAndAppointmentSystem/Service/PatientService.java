package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;

public interface PatientService {
    PatientInfo savePatientInfo(PatientInfo patientInfo);
    void deletePatientById(Long id);
    PatientInfo getPatientInfoById(Long id);
    List<PatientInfo> getAllPatients();
    PatientInfo updatePatientById(PatientInfo patientInfo);
}
