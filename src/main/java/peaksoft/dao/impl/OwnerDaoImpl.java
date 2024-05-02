package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.OwnerDao;
import peaksoft.entities.Agency;
import peaksoft.entities.House;
import peaksoft.entities.Owner;

import java.time.LocalDate;
import java.util.*;

public class OwnerDaoImpl implements OwnerDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();

    @Override
    public String saveOwner(Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newOwner);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success saved!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String saveOwner(Owner newOwner, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            newOwner.setHouse(Collections.singletonList(newHouse));
            newHouse.setOwner(newOwner);
            entityManager.persist(newOwner);
            entityManager.persist(newHouse);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public Optional<Owner> findOwnerById(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(owner);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Owner> findAllOwners() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Owner> owners = entityManager.createQuery("select o from Owner o", Owner.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return owners;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String updateOwnerById(Long ownerId, Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner findOwner = entityManager.find(Owner.class, ownerId);
            findOwner.setFirstName(newOwner.getFirstName());
            findOwner.setLastName(newOwner.getLastName());
            findOwner.setEmail(newOwner.getEmail());
            findOwner.setDateOfBirth(newOwner.getDateOfBirth());
            findOwner.setGender(newOwner.getGender());
            entityManager.getTransaction().commit();
            return "Successfully updated!!!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String deleteOwnerById(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            entityManager.remove(owner);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success deleted!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner findOwner = entityManager.find(Owner.class, ownerId);
            Agency findAgency = entityManager.find(Agency.class, agencyId);
            findOwner.getAgencies().add(findAgency);
            findAgency.getOwners().add(findOwner);
            entityManager.getTransaction().commit();
            return "Successfully assigned!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<Owner> getOwnersByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Owner> owners = entityManager.createQuery("select o from Owner o join o.agencies a where a.id =:agencyId", Owner.class)
                    .setParameter("agencyId", agencyId)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return owners;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, Integer> getOwnerOnlyNameAndAge() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<String, Integer> namesAndAges = new HashMap<>();
        try {
            entityManager.getTransaction().begin();
            List<Owner> allOwners = entityManager.createQuery("select o from Owner o", Owner.class)
                    .getResultList();
            for (Owner allOwner : allOwners) {
                namesAndAges.put(allOwner.getFirstName(), LocalDate.now().getYear() - allOwner.getDateOfBirth().getYear());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
            return null;
    }
}
