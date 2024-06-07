package JFS6WDE.PatientMedicineAndAppointmentSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Registration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.User;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Service.UserServiceImpl;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class logintest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PatientRepository infoRepo;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private Registration registrationDto;

    @BeforeEach
    public void setUp() {
        registrationDto = new Registration();
        registrationDto.setPatientName("John Doe");
        registrationDto.setContactInfo("1234567890");
        registrationDto.setEmail("john@example.com");
        registrationDto.setPassword("password123");
    }

    @Test
    public void testSave_Success() {
        // Mock password encoding
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");

        // Mock repository save methods
        when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        when(infoRepo.save(any(PatientInfo.class))).thenAnswer(i -> i.getArguments()[0]);

        // Call save method
        User savedUser = userService.save(registrationDto);

        // Assertions
        assertNotNull(savedUser);
        assertNotNull(savedUser.getPatientInfo());
        assertEquals("John Doe", savedUser.getPatientName());
        assertEquals("1234567890", savedUser.getContactInfo());
        assertEquals("john@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());

        // Verify that save methods were called
        verify(userRepo).save(any(User.class));
        verify(infoRepo).save(any(PatientInfo.class));
    }
}
