package peaksoft.service.impl;

import peaksoft.dao.HouseDao;
import peaksoft.dao.impl.HouseDaoimpl;
import peaksoft.entities.House;
import peaksoft.service.HouseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HouseServiceImpl implements HouseService {
    HouseDao houseDao = new HouseDaoimpl();
    @Override
    public String saveHouse(Long ownerId, House newHouse) {
        return houseDao.saveHouse(ownerId, newHouse);
    }

    @Override
    public Optional<House> findHouseById(Long houseId) {
        return houseDao.findHouseById(houseId);
    }

    @Override
    public List<House> findAllHouse() {
        return houseDao.findAllHouse();
    }

    @Override
    public String updateHouseById(Long houseId, House newHouse) {
        return houseDao.updateHouseById(houseId,newHouse);
    }

    @Override
    public String deleteHouseById(Long houseId) {
        return houseDao.deleteHouseById(houseId);
    }

    @Override
    public List<House> getHousesInRegion(String region) {
        return houseDao.getHousesInRegion(region);
    }

    @Override
    public List<House> getallHousesByAgencyId(Long agencyId) {
        return houseDao.getallHousesByAgencyId(agencyId);
    }

    @Override
    public List<House> getallHousesByOwnerId(Long ownerId) {
        return houseDao.getallHousesByOwnerId(ownerId);
    }

    @Override
    public List<House> housesBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return houseDao.housesBetweenDates(fromDate, toDate);
    }
}
