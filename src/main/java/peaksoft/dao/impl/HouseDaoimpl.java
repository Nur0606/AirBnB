package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.HouseDao;
import peaksoft.entities.House;
import peaksoft.entities.Owner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HouseDaoimpl implements HouseDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();

    @Override
    public String saveHouse(Long ownerId, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            owner.getHouse().add(newHouse);
            newHouse.setOwner(owner);
            entityManager.persist(newHouse);
            entityManager.getTransaction().commit();
            return "Successfully saved!";
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + r.getMessage();
        }
    }

    @Override
    public Optional<House> findHouseById(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, houseId);
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(house);
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + r.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<House> findAllHouse() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("select h from House h", House.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return houses;
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + r.getMessage());
        }
        return null;
    }

    @Override
    public String updateHouseById(Long houseId, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House findHouse = entityManager.find(House.class, houseId);
            findHouse.setHouseType(newHouse.getHouseType());
            findHouse.setPrice(newHouse.getPrice());
            findHouse.setRating(newHouse.getRating());
            findHouse.setDescription(newHouse.getDescription());
            findHouse.setRoom(newHouse.getRoom());
            findHouse.setFurniture(newHouse.isFurniture());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated!!!";
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + r.getMessage();
        }
    }

    @Override
    public String deleteHouseById(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, houseId);
            entityManager.remove(house);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success deleted!";
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + r.getMessage();
        }
    }

    @Override
    public List<House> getHousesInRegion(String region) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("select h from House h where h.address.region =:region ", House.class).setParameter("region", region).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return houses;
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + r.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getallHousesByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("select h from House h where h.address.agency.id =:agencyId", House.class).setParameter("agencyId", agencyId).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return houses;
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + r.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getallHousesByOwnerId(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("select h from House h where h.owner.id =:ownerId", House.class).setParameter("ownerId", ownerId).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return houses;
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + r.getMessage());
        }
        return null;
    }

    @Override
    public List<House> housesBetweenDates(LocalDate fromDate, LocalDate toDate) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("select h from House h inner join Rentinfo  r on r.house=h.id where  between  :fromDate and :toDate", House.class)
                    .setParameter("fromDate", fromDate)
                    .setParameter("toDate", toDate)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return houses;
        } catch (Exception r) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + r.getMessage());
        }
        return null;
    }
}
