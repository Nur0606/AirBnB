package peaksoft.dao;

import peaksoft.entities.House;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HouseDao {
    String saveHouse(Long ownerId, House newHouse);
    Optional<House> findHouseById(Long houseId);
    List<House> findAllHouse();
    String updateHouseById(Long houseId, House newHouse);
    String deleteHouseById(Long houseId);
    List<House> getHousesInRegion(String region);
    List<House> getallHousesByAgencyId(Long agencyId);
    List<House> getallHousesByOwnerId(Long ownerId);
    List<House> housesBetweenDates(LocalDate fromDate, LocalDate toDate);
}
