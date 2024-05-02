package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "agensies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Agency{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agency_seg")
    @SequenceGenerator(name = "agency_seg",sequenceName = "agency_seg",allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.REMOVE})
    private Address address;
    @ManyToMany(mappedBy = "agencies",cascade = {
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH})
    private List<Owner> owners;



    public Agency(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
        @Override
        public String toString() {
            return "Agency{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
}
