package peaksoft.service.impl;

import peaksoft.dao.AddressDao;
import peaksoft.dao.impl.AddressDaoImpl;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;
import peaksoft.service.AddressService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();
    @Override
    public Optional<Address> findAddressById(Long addressId) {
        Address address = null;
        try {
            address = addressDao.findAddressById(addressId)
                    .orElseThrow(() ->
                            new RuntimeException("Address with id: " + addressId + " not found!!!"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(address);
    }
    @Override
    public List<Address> findAllAddresses(){
        return addressDao.findAllAddresses();
    }
    @Override
    public String updateAddressById(Long addressId, Address newAddress) {
        return addressDao.updateAddressById(addressId, newAddress);
    }

    @Override
    public Map<Address, Agency> findAddressWithAgency() {
        return addressDao.findAddressWithAgency();
    }

    @Override
    public Integer countAgenciesInTheCity(String city) {
        List<Address> allAddresses = findAllAddresses();
        for (Address allAddress : allAddresses) {
            if (!allAddress.getCity().equalsIgnoreCase(city)){
                System.out.println("Incorrect choice!!!");
            }
        }
        return addressDao.countAgenciesInTheCity(city);
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        return addressDao.groupByRegion();
    }
}
