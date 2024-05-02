package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.HouseType;
import java.math.BigDecimal;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "house_seg")
    @SequenceGenerator(name = "house_seg",sequenceName = "house_seg",allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private BigDecimal price;
    private double rating;
    private String description;
    private int room ;
    private boolean furniture;
    @OneToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.DETACH})
    private Address address;
    @ManyToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.DETACH})
    private Owner owner;

    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }
}
