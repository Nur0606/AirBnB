package peaksoft.service.impl;

import peaksoft.dao.CustomerDao;
import peaksoft.dao.impl.CustomerDaoImpl;
import peaksoft.entities.Customer;
import peaksoft.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public String saveCustomer(Customer newCustomer) {
        return customerDao.saveCustomer(newCustomer);
    }

    @Override
    public Optional<Customer> findCustomerById(Long customerId) {
        return customerDao.findCustomerById(customerId);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerDao.findAllCustomers();
    }

    @Override
    public String updateCustomerById(Long customerId, Customer newCustomer) {
        return customerDao.updateCustomerById(customerId, newCustomer);
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        return customerDao.deleteCustomerById(customerId);
    }
}
