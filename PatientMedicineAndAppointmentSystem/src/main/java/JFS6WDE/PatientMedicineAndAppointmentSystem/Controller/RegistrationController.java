package JFS6WDE.PatientMedicineAndAppointmentSystem.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Registration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.User;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Service.UserService;

@Controller
public class RegistrationController {
    private final UserService userService;

    // Constructor injection
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // Method to show the registration form
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Registration());
        return "/registration";
    }

    // Method to handle form submission
    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") Registration registrationDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(registrationDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", "Something wrong",
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", registrationDto);
            return "/registration";
        }

        userService.save(registrationDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<Registration> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "users";
    }
}