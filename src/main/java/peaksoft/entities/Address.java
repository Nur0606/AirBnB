package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_seg")
    @SequenceGenerator(name = "address_seg",sequenceName = "address_seg",allocationSize = 1)
    private Long id;
    private String city;
    private String region;
    private String street;
    @OneToOne(mappedBy = "address",cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private Agency agency;

    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;

    }
}
