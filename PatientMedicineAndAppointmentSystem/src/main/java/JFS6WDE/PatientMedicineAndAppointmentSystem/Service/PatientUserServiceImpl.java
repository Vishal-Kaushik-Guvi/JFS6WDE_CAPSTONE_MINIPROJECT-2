package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientRegistration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.PatientUser;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Role;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientUserRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class PatientUserServiceImpl implements PatientUserService {

    @Autowired
    private PatientUserRepository userRepo;

    @Autowired
    private PatientRepository infoRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public PatientUser save(PatientRegistration registrationDto) {
        PatientUser user = new PatientUser(
                registrationDto.getPatientName(),
                registrationDto.getContactInfo(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        PatientInfo patientInfo = new PatientInfo(
                registrationDto.getPatientName(),
                registrationDto.getContactInfo());

        user.setPatientInfo(patientInfo);
        patientInfo.setPatientUser(user);

        userRepo.save(user);
        infoRepo.save(patientInfo);

        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String patientName) throws UsernameNotFoundException {
        PatientUser user = userRepo.findByPatientName(patientName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getPatientName(),
                user.getPassword(),
                mapRolesAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
