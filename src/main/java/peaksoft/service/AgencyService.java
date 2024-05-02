package peaksoft.service;

import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {
    String saveAgency(Agency agency, Address address);
    List<Agency> getAllAgency();
    Optional<Agency> findAgencyById(Long agencyId);
    String updateAgencyById(Long agencyId, Agency newAgency);
    String deleteAgencyById(Long agencyId);
}
