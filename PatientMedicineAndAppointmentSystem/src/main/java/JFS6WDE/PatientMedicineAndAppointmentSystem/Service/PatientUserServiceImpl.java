package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientRegistration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientUser;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Role;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientUserRepository;
import ch.qos.logback.classic.Logger;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientRepository;

@Service
public class PatientUserServiceImpl implements PatientUserService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PatientUserServiceImpl.class);

    @Autowired
    private PatientUserRepository userRepo;

    @Autowired
    private PatientRepository infoRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public PatientUser save(PatientRegistration registrationDto) {
        PatientUser patientuser = new PatientUser(
                registrationDto.getPatientname(),
                registrationDto.getEmail(),
                registrationDto.getContactinfo(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        PatientInfo patientInfo = new PatientInfo(
                registrationDto.getPatientname(),
                registrationDto.getContactinfo());

        patientuser.setPatientInfo(patientInfo);
        patientInfo.setPatientUser(patientuser);

        userRepo.save(patientuser);
        infoRepo.save(patientInfo);

        return patientuser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by username: {}", email);
        PatientUser user = userRepo.findByEmail(email);
        if (user == null) {
            logger.error("User not found: {}", email);
            throw new UsernameNotFoundException("User does not exist!");
        }
        logger.debug("User found: {}", user);
        logger.debug("User roles: {}", user.getRoles());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    
        private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
