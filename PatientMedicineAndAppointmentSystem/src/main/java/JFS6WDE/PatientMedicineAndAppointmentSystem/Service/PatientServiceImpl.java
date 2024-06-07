package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.UserDefinedException.ResourceNotFoundException;

@Service
public class PatientServiceImpl implements PatientService {
	
    @Autowired
    private PatientRepository patientRepo;

    @Override
    public PatientInfo savePatientInfo(PatientInfo patientInfo) {
        return patientRepo.save(patientInfo);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepo.deleteById(id);
    }

    @Override
    public PatientInfo getPatientInfoById(Long id) {
        return patientRepo.findById(id).get();
    }

    @Override
    public List<PatientInfo> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public PatientInfo updatePatientById(PatientInfo updatedPatientInfo) {
        Optional<PatientInfo> existingPatientOptional = patientRepo.findById(updatedPatientInfo.getId());
        
        if (existingPatientOptional.isPresent()) {
            PatientInfo existingPatient = existingPatientOptional.get();
            
            // Update the fields of the existing patient object
            existingPatient.setPatientName(updatedPatientInfo.getPatientName());
            existingPatient.setContactInfo(updatedPatientInfo.getContactInfo());
            existingPatient.setMedicalHistory(updatedPatientInfo.getMedicalHistory());
            
            // Save the updated patient object to the database
            PatientInfo updatedPatient = patientRepo.save(existingPatient);
            
            return updatedPatient;
        } else {
            // Handle the case where the patient with the given ID is not found
            throw new ResourceNotFoundException("Patient not found with ID: " + updatedPatientInfo.getId());
        }
    }
    
}
