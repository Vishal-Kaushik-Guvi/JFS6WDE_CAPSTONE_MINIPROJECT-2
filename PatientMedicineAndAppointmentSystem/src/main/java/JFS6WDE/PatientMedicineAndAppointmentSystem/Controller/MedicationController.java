package JFS6WDE.PatientMedicineAndAppointmentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.Medication;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.MedicationRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientRepository;
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

    @GetMapping("/edit/{id}")
    public String editMedication(@PathVariable Long id, Model model) {
        model.addAttribute("medicationToEdit", medicationService.getMedicationById(id));
        model.addAttribute("medications", medicationService.getAllMedications());
        return "medications";
    }

    @PostMapping("/update")
    public String updateMedication(@RequestParam Long id, @RequestParam String name, @RequestParam String dosage, @RequestParam Long patientInfo) {
        Medication medication = new Medication();
        medication.setId(id);
        medication.setName(name);
        medication.setDosage(dosage);
        medicationService.updateMedicationById(medication);
        return "redirect:/medications";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return "redirect:/medications";
    }
}