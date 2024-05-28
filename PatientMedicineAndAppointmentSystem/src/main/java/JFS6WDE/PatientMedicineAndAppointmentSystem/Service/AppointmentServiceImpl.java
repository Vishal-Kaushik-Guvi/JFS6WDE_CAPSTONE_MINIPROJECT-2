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
    public AppointmentBooking createAppointmentBooking(AppointmentBooking booking) {
        return appointmentRepository.save(booking);
    }

    @Override
    public List<AppointmentBooking> findAppointmentBookings() {
        return appointmentRepository.findAll();
    }

}
