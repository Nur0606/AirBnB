package peaksoft.dao;

import peaksoft.entities.Rentinfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoDao {
    List<Rentinfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate);
    Long housesByAgencyIdAndDate(Long agencyId);
}
