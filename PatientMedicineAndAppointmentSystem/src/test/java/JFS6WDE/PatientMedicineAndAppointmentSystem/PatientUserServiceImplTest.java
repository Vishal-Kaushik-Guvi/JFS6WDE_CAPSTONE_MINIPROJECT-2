package JFS6WDE.PatientMedicineAndAppointmentSystem;
   
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientUser;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Role;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientUserRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Service.PatientUserServiceImpl;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientUserServiceImplTest {

    @Mock
    private PatientUserRepository userRepo;

    @Mock
    private PatientRepository infoRepo;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private PatientUserServiceImpl userService;

    private PatientUser user;

    @BeforeEach
    public void setUp() {
        Role role = new Role("ROLE_USER");
        Collection<Role> roles = Arrays.asList(role);
        user = new PatientUser("John Doe", "john@example.com", "1234567890", "password123",Collections.emptyList());
        user.setId(1L);
        user.setRoles(roles);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        when(userRepo.findByEmail(anyString())).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername("john@example.com");

        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepo.findByEmail(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistent@example.com");
        });

        
    }
}