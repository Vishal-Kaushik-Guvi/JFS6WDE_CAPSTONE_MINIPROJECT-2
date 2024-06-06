package JFS6WDE.PatientMedicineAndAppointmentSystem.DTO;


import java.util.Collection;

import JFS6WDE.PatientMedicineAndAppointmentSystem.Entities.PatientInfo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String patientname;

    private String email;

    private String contactinfo;
     
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToOne(mappedBy = "patientUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private PatientInfo patientInfo;

    public PatientUser(String patientName, String email, String contactInfo, String password, Collection<Role> roles) {
        this.patientname = patientName;
        this.contactinfo = contactInfo;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
  
}
