package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.AppointmentBooking;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppointmentBooking saveAppointmentBooking(AppointmentBooking appointmentBooking) {
        return appointmentRepository.save(appointmentBooking);
    }

    @Override
    public void deleteAppointmentBooking(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentBooking getAppointmentBookingById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<AppointmentBooking> getAllAppointmentBookings() {
        return appointmentRepository.findAll();
    }

}
