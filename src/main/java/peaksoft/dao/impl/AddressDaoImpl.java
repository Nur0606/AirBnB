package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.AddressDao;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.*;

public class AddressDaoImpl implements AddressDao{
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();
    @Override
    public Optional<Address> findAddressById(Long addressId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, addressId);
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(address);
        }catch (HibernateException e ){
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
            System.out.println("Error: "+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Address> findAllAddresses() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Address> list = entityManager.createQuery("select a from Address a", Address.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return list;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @Override
    public String updateAddressById(Long addressId, Address newAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address findAddress = entityManager.find(Address.class, addressId);
            findAddress.setCity(newAddress.getCity());
            findAddress.setRegion(newAddress.getRegion());
            findAddress.setStreet(newAddress.getStreet());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated!!!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public Map<Address, Agency> findAddressWithAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<Address, Agency> addresses = new HashMap<>();
        try {
            entityManager.getTransaction().begin();
            List<Address> resultList = entityManager.createQuery("select distinct a from Address a join a.agency", Address.class).getResultList();
            for (Address address : resultList) {
                addresses.put(address, address.getAgency());
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return addresses;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Integer countAgenciesInTheCity(String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int count = 0;
        try {
            entityManager.getTransaction().begin();
            count = entityManager.createQuery("select count(a.id) from Agency a where a.address.city =:city", Integer.class).setParameter("city", city).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return  count;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<String, List<Agency>> map = new HashMap<>();
        try {
            entityManager.getTransaction().begin();
            List<Address> addresses = entityManager.createQuery("select a from Address a", Address.class).getResultList();
            List<Agency> agencies = new ArrayList<>();
            for (Address address : addresses) {
                agencies.add(address.getAgency());
                map.put(address.getRegion(), agencies);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
