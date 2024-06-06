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
        User patientuser = new User(
                registrationDto.getPatientname(),
                registrationDto.getEmail(),
                registrationDto.getContactinfo(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role()));

        Role role = roleRepo.findByRole("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        patientuser.setRoles(Arrays.asList(role));

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
        User user = userRepo.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
        return mapRoles;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<Registration> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map((user) -> maptoRegistration(user))
                .collect(Collectors.toList());
    }

    private Registration maptoRegistration(User user) {
        Registration userDto = new Registration();
        String[] str = user.getPatientname().split(" ");
        userDto.setPatientname(str[0]);
        userDto.setContactinfo(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        return roleRepo.save(role);
    }
}
