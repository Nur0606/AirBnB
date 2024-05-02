package peaksoft.service.impl;

import peaksoft.dao.AddressDao;
import peaksoft.dao.AgencyDao;
import peaksoft.dao.impl.AddressDaoImpl;
import peaksoft.dao.impl.AgencyDaoImpl;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;
import peaksoft.service.AgencyService;

import java.util.List;
import java.util.Optional;

public class AgencyServiceImpl implements AgencyService {
    AgencyDao agencyDao = new AgencyDaoImpl();
    AddressDao addressDao = new AddressDaoImpl();
    @Override
    public String saveAgency(Agency agency, Address address) {
        List<Address> allAddresses = addressDao.findAllAddresses();
        for (Address allAddress : allAddresses) {
            if (allAddress.getStreet().equals(address.getStreet())) {
                return address.getStreet();
            }
        }
        if (agency.getPhoneNumber().startsWith("+996") && agency.getPhoneNumber().length() == 13) {
            return agencyDao.saveAgency(agency, address);
        }else {
            return "no saved!";
        }
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyDao.getAllAgency();
    }

    @Override
    public Optional<Agency> findAgencyById(Long agencyId) {
        return agencyDao.findAgencyById(agencyId);
    }

    @Override
    public String updateAgencyById(Long agencyId, Agency newAgency) {
       Optional<Agency> findAgency = findAgencyById(agencyId);
        if (findAgency == null) {
            return "Error";
        }
        if (newAgency.getPhoneNumber().startsWith("+996") && newAgency.getPhoneNumber().length() == 13) {
            return agencyDao.updateAgencyById(agencyId, newAgency);
        }else {
            return "phone number not Correct!";
        }
    }

    @Override
    public String deleteAgencyById(Long agencyId) {
        return agencyDao.deleteAgencyById(agencyId);
    }
}
