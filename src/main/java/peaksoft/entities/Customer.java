package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_seg")
    @SequenceGenerator(name = "customer_seg",sequenceName = "customer_seg",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    @Enumerated
    private Gender gender;
    private String nationality;
    @Enumerated
    private FamilyStatus familyStatus;
    @OneToMany(mappedBy = "customers",cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.DETACH})
    private List<Rentinfo> rentinfos;

    public Customer(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, String nationality, FamilyStatus nikesiBar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
    }
}
