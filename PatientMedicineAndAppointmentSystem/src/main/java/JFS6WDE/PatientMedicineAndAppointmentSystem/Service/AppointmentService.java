package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.AppointmentBooking;

public interface AppointmentService {
  AppointmentBooking createAppointmentBooking(AppointmentBooking booking);
  List<AppointmentBooking> findAppointmentBookings();
}
