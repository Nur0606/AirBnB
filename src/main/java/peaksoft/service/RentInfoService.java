package peaksoft.service;

import peaksoft.entities.Rentinfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoService {
    List<Rentinfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate);
    Long housesByAgencyIdAndDate(Long agencyId);
}
