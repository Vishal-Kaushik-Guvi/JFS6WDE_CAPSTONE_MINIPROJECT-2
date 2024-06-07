package JFS6WDE.PatientMedicineAndAppointmentSystem.Service;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Registration;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.User;
import JFS6WDE.PatientMedicineAndAppointmentSystem.DTO.Role;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.PatientRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.RoleRepository;
import JFS6WDE.PatientMedicineAndAppointmentSystem.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PatientRepository infoRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public User save(Registration registrationDto) {
        User patientUser = new User(
                registrationDto.getPatientName(),
                registrationDto.getEmail(),
                registrationDto.getContactInfo(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role()));

        Role role = roleRepo.findByRole("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        patientUser.setRoles(Arrays.asList(role));

        PatientInfo patientInfo = new PatientInfo(
                registrationDto.getPatientName(),
                registrationDto.getContactInfo());

        patientUser.setPatientInfo(patientInfo);
        patientInfo.setUser(patientUser);

        userRepo.save(patientUser);
        infoRepo.save(patientInfo);

        return patientUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepo.findByEmail(email);

            if (user != null) {
                return new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        mapRolesToAuthorities(user.getRoles()));
            } else {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error occurred while fetching user details.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<Registration> findAllUser() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map((user) -> maptoRegistration(user))
                .collect(Collectors.toList());
    }

    private Registration maptoRegistration(User user) {
        Registration userDto = new Registration();
        String[] nameParts = user.getPatientName().split(" ");
        userDto.setPatientName(nameParts[0]);
        if (nameParts.length > 1) {
            userDto.setContactInfo(nameParts[1]);
        }
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        return roleRepo.save(role);
    }
}

