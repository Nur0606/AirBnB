package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.CustomerDao;
import peaksoft.entities.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();
    @Override
    public String saveCustomer(Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCustomer);
            entityManager.getTransaction().commit();
            entityManager.close();
            return " Successfully saved!!!";
        }catch (HibernateException e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Error: "+e.getMessage();
        }
    }
    @Override
    public Optional<Customer> findCustomerById(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(customer);
        }catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
            return Optional.empty();
    }

    @Override
    public List<Customer> findAllCustomers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            List<Customer> list = entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return list;
        }catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String updateCustomerById(Long customerId, Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setEmail(newCustomer.getEmail());
            customer.setDateOfBirth(newCustomer.getDateOfBirth());
            customer.setGender(newCustomer.getGender());
            customer.setNationality(newCustomer.getNationality());
            customer.setFamilyStatus(newCustomer.getFamilyStatus());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated!";
        }catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success deleted!";
        }catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }
    }

