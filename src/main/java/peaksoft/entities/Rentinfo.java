package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "rent_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rentinfo{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rentin_seg")
    @SequenceGenerator(name = "rentin_seg",sequenceName = "rentin_seg",allocationSize = 1)
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @ManyToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private Owner owner;
    @ManyToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private Customer customers;
    @OneToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private House house;
    @ManyToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private Agency agencies;

    public Rentinfo(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
