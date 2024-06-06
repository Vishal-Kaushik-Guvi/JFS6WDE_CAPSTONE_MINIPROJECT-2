package JFS6WDE.PatientMedicineAndAppointmentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
            PatientInfo patientInfo = patientService.getPatientInfoById(id);
            model.addAttribute("patientInfo", patientInfo);
            return "edit-patient";
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }

    @PostMapping("/patientupdate/{id}")
    public String updatePatient(@ModelAttribute("patient") PatientInfo info) {
       patientService.updatePatientById(info);
       return "updatepatient";
    }

}
