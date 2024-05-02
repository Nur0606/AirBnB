package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.RentInfoDao;
import peaksoft.entities.Rentinfo;

import java.time.LocalDate;
import java.util.List;

public class RentInfoDaoImpl implements RentInfoDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();
    @Override
    public List<Rentinfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            List<Rentinfo> rentinfos = entityManager.createQuery("select r from Rentinfo r where r.checkOut between :fromDate and :toDate", Rentinfo.class)
                    .setParameter("fromDate", fromDate)
                    .setParameter("toDate", toDate)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return rentinfos;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Long housesByAgencyIdAndDate(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Long countHouse = 0L;
        try {
            entityManager.getTransaction().begin();
            countHouse = entityManager.createQuery("select count(r) from Rentinfo r where r.agencies.id =:agencyId and r.checkIn <=:currentDate and r.checkOut >=:currentDate ", Long.class)
                    .setParameter("agencyId", agencyId)
                    .setParameter("currentDate", LocalDate.now())
                    .getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return countHouse;
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
