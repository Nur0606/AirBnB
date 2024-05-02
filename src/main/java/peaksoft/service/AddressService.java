package peaksoft.service;

import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AddressService {
    Optional<Address> findAddressById(Long addressId);
    List<Address> findAllAddresses();
    String updateAddressById(Long addressId, Address newAddress);
    Map<Address, Agency> findAddressWithAgency();
    Integer countAgenciesInTheCity(String city);
    Map<String, List<Agency>> groupByRegion();
}
