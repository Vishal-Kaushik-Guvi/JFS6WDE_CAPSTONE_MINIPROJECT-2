package JFS6WDE.PatientMedicineAndAppointmentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Medication;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Service.MedicationServiceImplt;

@Controller
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationServiceImplt medicationService;

    @GetMapping
    public String listMedications(Model model) {
        model.addAttribute("medications", medicationService.getAllMedications());
        return "medications";
    }

    @PostMapping("/add")
    public String addMedication(@RequestParam String name, @RequestParam String dosage, @RequestParam Long patientInfo) {
        Medication medication = new Medication();
        medication.setName(name);
        medication.setDosage(dosage);
        medicationService.saveMedication(medication);
        return "redirect:/medications";
    }

    @GetMapping("/updated/{id}")
    public String editMedication(@PathVariable Long id, Model model) {
        model.addAttribute("medication", medicationService.getMedicationById(id));
        return "medications";
    }

    @PostMapping("/update")
    public String updateMedication(@ModelAttribute("medication") Medication med) {
      medicationService.updateMedicationById(med);
      return "updatemedications";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return "redirect:/medications";
    }
}