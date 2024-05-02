package peaksoft.service.impl;

import peaksoft.dao.RentInfoDao;
import peaksoft.dao.impl.RentInfoDaoImpl;
import peaksoft.entities.Rentinfo;
import peaksoft.service.RentInfoService;

import java.time.LocalDate;
import java.util.List;

public class RentInfoServiceImpl implements RentInfoService {
    RentInfoDao rentInfoDao = new RentInfoDaoImpl();
    @Override
    public List<Rentinfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return rentInfoDao.rentInfoBetweenDates(fromDate, toDate);
    }

    @Override
    public Long housesByAgencyIdAndDate(Long agencyId) {
        return rentInfoDao.housesByAgencyIdAndDate(agencyId);
    }
}
