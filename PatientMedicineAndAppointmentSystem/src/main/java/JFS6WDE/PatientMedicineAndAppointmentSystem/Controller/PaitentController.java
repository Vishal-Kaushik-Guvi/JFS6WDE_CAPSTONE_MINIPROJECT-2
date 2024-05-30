package JFS6WDE.PatientMedicineAndAppointmentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Service.PatientServiceImpl;
import JFS6WDE.PatientMedicineAndAppointmentSystem.UserDefinedException.ResourceNotFoundException;

@Controller
public class PaitentController {

    @Autowired
    private PatientServiceImpl patientService;

    @GetMapping("/patientInfo")
    public String showPatientInfo(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patientinfo";
    }
    
    @GetMapping("/patients/{id}/edit")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        try {
            PatientInfo patientInfo = patientService.getPatientById(id);
            model.addAttribute("patientInfo", patientInfo);
            return "edit-patient";
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }

    @PostMapping("/patients/{id}/update")
    public String updatePatient(@PathVariable Long id, PatientInfo updatedPatientInfo, Model model) {
        try {
            PatientInfo updatedPatient = patientService.updatePatientById(updatedPatientInfo);
            model.addAttribute("patientInfo", updatedPatient);
            return "patient-details";
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }

}
