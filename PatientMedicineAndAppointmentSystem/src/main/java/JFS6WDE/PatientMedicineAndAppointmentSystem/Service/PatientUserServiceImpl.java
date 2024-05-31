package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import java.util.Collections;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientRegistration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientUser;
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
        PatientUser user = new PatientUser(
                registrationDto.getPatientname(),
                registrationDto.getContactInfo(),
                passwordEncoder.encode(registrationDto.getPassword()));

        PatientInfo patientInfo = new PatientInfo(
                registrationDto.getPatientname(),
                registrationDto.getContactInfo());

        user.setPatientInfo(patientInfo);
        patientInfo.setPatientUser(user);

        userRepo.save(user);
        infoRepo.save(patientInfo);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String patientname) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by username: {}", patientname);
        PatientUser user = userRepo.findByPatientname(patientname);
        if (user == null) {
            logger.error("User not found: {}", patientname);
            throw new UsernameNotFoundException("User does not exist!");
        }
        logger.debug("User found: {}", user);
        return new org.springframework.security.core.userdetails.User(
                user.getPatientname(),
                user.getPassword(),
                Collections.emptyList());
    }
}
