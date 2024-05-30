package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.List;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.AppointmentBooking;

public interface AppointmentService {
  AppointmentBooking saveAppointmentBooking(AppointmentBooking appointmentBooking);
  void deleteAppointmentBooking(Long id);
  AppointmentBooking getAppointmentBookingById(Long id);
  List<AppointmentBooking> getAllAppointmentBookings();

}
