package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Gender;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Owner{
    @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "owner_seg")
@SequenceGenerator(name = "owner_seg",sequenceName = "owner_seg",allocationSize = 1)
private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    @Enumerated
    private Gender gender;
    @OneToMany(mappedBy = "owner",cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private List<House> house;
    @OneToMany(mappedBy = "owner",cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.DETACH})
    private List<Rentinfo> rentinfos;
    @ManyToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private List<Agency>agencies;

    public Owner(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
