package JFS6WDE.PatientMedicineAndAppointmentSystem.Controller;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.AppointmentBooking;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String viewAllAppointments(Model model) {
        List<AppointmentBooking> appointments = appointmentService.getAllAppointmentBookings();
        model.addAttribute("appointments", appointments);
        return "viewappointments";
    }

    @GetMapping("/addAppointment")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("appointment", new AppointmentBooking());
        return "viewappointments";
    }

    @PostMapping("/addAppointment")
    public String addAppointment(@ModelAttribute("appointment") AppointmentBooking appointmentBooking) {
        appointmentService.saveAppointmentBooking(appointmentBooking);
        return "redirect:/addappointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.deleteAppointmentBooking(id);
        return "redirect:/viewappointments";
    }
}
