package peaksoft.service.impl;

import peaksoft.dao.OwnerDao;
import peaksoft.dao.impl.OwnerDaoImpl;
import peaksoft.entities.House;
import peaksoft.entities.Owner;
import peaksoft.service.OwnerService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OwnerServiceImpl implements OwnerService {
    OwnerDao ownerDao = new OwnerDaoImpl();
    @Override
    public String saveOwner(Owner newOwner) {
        return ownerDao.saveOwner(newOwner);
    }

    @Override
    public String saveOwner(Owner newOwner, House newHouse) {
        return ownerDao.saveOwner(newOwner,newHouse);
    }

    @Override
    public Optional<Owner> findOwnerById(Long ownerId) {
        return ownerDao.findOwnerById(ownerId);
    }

    @Override
    public List<Owner> findAllOwners() {
        return ownerDao.findAllOwners();
    }

    @Override
    public String updateOwnerById(Long ownerId, Owner newOwner) {
        return ownerDao.updateOwnerById(ownerId, newOwner);
    }

    @Override
    public String deleteOwnerById(Long ownerId) {
        return ownerDao.deleteOwnerById(ownerId);
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        return ownerDao.assignOwnerToAgency(ownerId, agencyId);
    }

    @Override
    public List<Owner> getOwnersByAgencyId(Long agencyId) {
        return ownerDao.getOwnersByAgencyId(agencyId);
    }

    @Override
    public Map<String, Integer> getOwnerOnlyNameAndAge() {
        return ownerDao.getOwnerOnlyNameAndAge();
    }
}
