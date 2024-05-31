package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Medication;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.MedicationRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.UserDefinedException.ResourceNotFoundException;

@Service
public class MedicationServiceImplt implements MedicationService {

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

    @Override
    public Medication updateMedicationById(Medication medic) {
        // Find the existing medication by ID using Optional
        Optional<Medication> optionalMedication = medicationRepository.findById(medic.getId());

        if (optionalMedication.isPresent()) {
            Medication existingMedication = optionalMedication.get();

            // Update the medication details
            existingMedication.setName(medic.getName());
            existingMedication.setDosage(medic.getDosage());
            existingMedication.setPatientInfo(medic.getPatientInfo());

            // Save the updated medication
            return medicationRepository.save(existingMedication);
        } else {
            throw new ResourceNotFoundException("Medication not found with id: " + medic.getId());
        }
    }
}
