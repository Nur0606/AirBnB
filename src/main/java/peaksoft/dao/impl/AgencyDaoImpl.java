package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.AgencyDao;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.List;
import java.util.Optional;

public class AgencyDaoImpl implements AgencyDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();
    @Override
    public String saveAgency(Agency agency, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(agency);
            entityManager.persist(address);
            agency.setAddress(address);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "success saved";
        }catch (HibernateException e ){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
                return "Error"+e.getMessage();
            }
        }
        return null;
    }

    @Override
    public List<Agency> getAllAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Agency> agencies = entityManager.createQuery("select a from Agency a ",Agency.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return agencies;
        }catch (HibernateException e ){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
                System.out.println("Error: "+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Optional<Agency> findAgencyById(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(agency);
        }catch (HibernateException e ){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: "+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String updateAgencyById(Long agencyId, Agency newAgency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency findAgency = entityManager.find(Agency.class, agencyId);
            findAgency.setName(newAgency.getName());
            findAgency.setPhoneNumber(newAgency.getPhoneNumber());
            entityManager.getTransaction().commit();
            return "successfully updated!!!";
        }catch (HibernateException e ){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: "+e.getMessage();
        }
    }

    @Override
    public String deleteAgencyById(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success deleted!";
        }catch (HibernateException e ){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: "+e.getMessage();
        }
    }
}
