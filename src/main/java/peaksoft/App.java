package peaksoft;
import peaksoft.config.DatabaseConfig;
import peaksoft.entities.*;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;
import peaksoft.enums.HouseType;
import peaksoft.service.*;
import peaksoft.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){

        AddressService addressService = new AddressServiceImpl();

        System.out.println(addressService.findAddressById(1L));
        System.out.println(addressService.findAllAddresses());
        System.out.println(addressService.updateAddressById(3L,
                new Address("osh", "kg", "oshhh")));
        System.out.println(addressService.findAddressWithAgency());
        System.out.println(addressService.countAgenciesInTheCity("Bishkek"));
        System.out.println(addressService.groupByRegion());


        AgencyService agencyService = new AgencyServiceImpl();
        System.out.println(agencyService.saveAgency(new Agency("n", "+996290180752"),
                new Address("jjn", "hbn", "hjm")));
        System.out.println(agencyService.findAgencyById(1L));
        System.out.println(agencyService.updateAgencyById(2L,
                new Agency("batken", "+996700294595")));
        System.out.println(agencyService.deleteAgencyById(3L));


        CustomerService customerService = new CustomerServiceImpl();
        System.out.println(customerService.saveCustomer(new Customer(
                "nurmuahmemd", "rusbaev", "n@gmail.com", LocalDate.of(2006, 11,22),
                Gender.MALE, "asia", FamilyStatus.NikesiBar
        )));
        System.out.println(customerService.saveCustomer(new Customer(
                "Nur", "Toi", "nwert@gmail.com", LocalDate.of(1998, 9,23),
                Gender.MALE, "Kyzgyz", FamilyStatus.NikesiBar
        )));
        System.out.println(customerService.findCustomerById(1L));
        System.out.println(customerService.findAllCustomers());
        System.out.println(customerService.updateCustomerById(1L, new Customer(
                "aaaa", "zzz", "aa@gmail.com", LocalDate.of(2025,2,5),
                Gender.MALE, "KG", FamilyStatus.NikesiJok
        )));
        System.out.println(customerService.deleteCustomerById(1L));
        System.out.println(customerService.deleteCustomerById(1L));



        HouseService houseService = new HouseServiceImpl();
        System.out.println(houseService.saveHouse(1L, new House(
                HouseType.House, BigDecimal.valueOf(194232), 4, "hosue", 3, false
        )));
        System.out.println(houseService.findHouseById(1L));

        System.out.println(houseService.findAllHouse());
        System.out.println(houseService.updateHouseById(1L, new House(
                HouseType.Apartment, BigDecimal.valueOf(50430000), 2,
                "pppp", 8, true
        )));
        System.out.println(houseService.deleteHouseById(1L));
        System.out.println(houseService.getHousesInRegion("batken"));
        System.out.println(houseService.getallHousesByAgencyId(1L));
        System.out.println(houseService.getallHousesByOwnerId(1L));
        System.out.println(houseService.housesBetweenDates(LocalDate.of(2025, 1, 1),
                LocalDate.of(2026, 1, 1)));

        OwnerService ownerService = new OwnerServiceImpl();
        System.out.println(ownerService.saveOwner(new Owner(
                "owner", "ovich", "o@gmailcom", LocalDate.of(2000,1,1),
                Gender.MALE
        )));
        System.out.println(ownerService.saveOwner(new Owner(
                "qwr", "qwer", "qwer@gmail.com", LocalDate.of(1998, 9, 23),
                Gender.MALE
        ), new House(
                HouseType.House, BigDecimal.valueOf(10), 5.6, "pokoijkijn", 3,
                true
        )));
        System.out.println(ownerService.deleteOwnerById(1L));
        System.out.println(ownerService.findOwnerById(1L));
        System.out.println(ownerService.findAllOwners());
        System.out.println(ownerService.updateOwnerById(1L, new Owner(
                "nur", "tofri", "nur@gsemail.com",
                LocalDate.of(1990,2,2),
                Gender.MALE
        )));
        System.out.println(ownerService.assignOwnerToAgency(1L, 1L));
        System.out.println(ownerService.getOwnersByAgencyId(1L));

        System.out.println(ownerService.getOwnerOnlyNameAndAge());


        RentInfoService rentInfoService = new RentInfoServiceImpl();
        System.out.println(rentInfoService.housesByAgencyIdAndDate(10L));

        System.out.println(rentInfoService.rentInfoBetweenDates(LocalDate.of(2024, 3, 2),
                LocalDate.of(2024, 4, 1)));
   }
}
