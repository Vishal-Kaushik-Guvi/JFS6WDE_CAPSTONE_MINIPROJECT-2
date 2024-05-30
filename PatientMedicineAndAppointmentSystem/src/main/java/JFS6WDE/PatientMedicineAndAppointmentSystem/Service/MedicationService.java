package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Medication;

public interface MedicationService {
    
    Medication saveMedication(Medication medication);
    void deleteMedication(Long id);
    Medication getMedicationById(Long id);
    List<Medication> getAllMedications();    
}
