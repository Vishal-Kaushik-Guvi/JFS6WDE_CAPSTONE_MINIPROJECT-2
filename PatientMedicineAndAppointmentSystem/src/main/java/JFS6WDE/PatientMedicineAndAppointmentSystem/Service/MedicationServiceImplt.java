package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Medication;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.MedicationRepository;

@Service
public class MedicationServiceImplt implements MedicationService{
   
    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Medication saveMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }

    @Override
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id).get();
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
}
