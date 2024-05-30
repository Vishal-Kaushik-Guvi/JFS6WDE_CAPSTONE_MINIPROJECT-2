package JFS6WDE.PatientMedicineAndAppointmentSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientRegistration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Service.PatientUserService;

@Controller
public class RegistrationController {
    private final PatientUserService userService;

    // Constructor injection
    public RegistrationController(PatientUserService userService) {
        this.userService = userService;
    }

    // Method to show the registration form
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new PatientRegistration());
        return "registration";
    }

    // Method to handle form submission
    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") PatientRegistration registrationDto) {
        System.out.println("password " + registrationDto.getPassword());
        // Call save to register the user
        userService.save(registrationDto);
        // Redirect to registration page with success message
        return "redirect:/registration?success";
    }
}
