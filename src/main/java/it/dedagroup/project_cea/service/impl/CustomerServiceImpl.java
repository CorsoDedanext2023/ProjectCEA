package it.dedagroup.project_cea.service.impl;

import java.time.LocalDate;
import java.util.List;

import it.dedagroup.project_cea.exception.model.UserNotFoundException;
import it.dedagroup.project_cea.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;

@Service
public class CustomerServiceImpl implements CustomerServiceDef{
	
	@Autowired
	CustomerRepository customerRepo;         //istanziato le repository(contenenti i metodi find)
	@Autowired
	InterventionRepository interventionRepo;
	@Autowired
	BillRepository billRepo;
	@Autowired
	ApartmentRepository apartmentRepo;
	@Autowired
	ScanRepository scanRepository;
	
	@Override
	public void saveCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public void modifyCustomer(Customer customer) {
		saveCustomer(customer);
	}

	@Override
	public void deleteCustomer(long id_customer) {
		Customer customer = findCustomerById(id_customer);
		customer.setAvailable(false);
		customerRepo.save(customer);
	}
	@Override
	public Bill payBill(long id_bill, LocalDate paymentDate) {
		Bill bill=billRepo.findById(id_bill).orElseThrow(() -> new NotValidDataException("Bill not found with id: "+id_bill));
		bill.setPaymentDay(paymentDate);
		return billRepo.save(bill);
	}

	@Override
	public Scan autoScan(Scan scan) {
		return scanRepository.save(scan);
	}

	@Override
	public Customer findCustomerByIdAndIsAvailableTrue(long id_customer) {
		return customerRepo.findCustomerByIdAndIsAvailableTrue(id_customer).orElseThrow(() -> new NotValidDataException("Customer not found with id: "+id_customer));
	}

	@Override
	public List<Customer> findAllAndIsAvailableTrue() {
		return customerRepo.findAll().stream().filter(Customer::isAvailable).toList();
	}

	@Override
	public Customer findCustomerById(long id_customer) {
		return customerRepo.findById(id_customer).orElseThrow(() -> new UserNotFoundException("Customer not found with id: "+id_customer));
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerRepo.findAll();
	}

	@Override
	public Customer findCustomerByUsernameAndPassword(String username, String password) {
		return customerRepo.findCustomerByUsernameAndPassword(username, password).orElseThrow(() -> new UserNotFoundException("Customer's username and/or password invalid"));
	}
	
	@Override
	public Customer findCustomerByUsername(String username) {
		return customerRepo.findCustomerByUsername(username).orElseThrow(() -> new UserNotFoundException("Customer not found with username: "+username));
	}

	@Override
	public Customer findCustomerByTaxCode(String taxCode) {
		return customerRepo.findCustomerByTaxCode(taxCode).orElseThrow(() -> new UserNotFoundException("Customer not found with tax code: "+taxCode));
	}

	@Override
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname) {
		return customerRepo.findAllCustomerByNameAndSurname(name, surname);
	}

	@Override
	public Customer findCustomerByApartments_Id(long id_apartment) {
		return customerRepo.findCustomerByApartments_Id(id_apartment)
				.orElseThrow(() -> new UserNotFoundException("Customer not found with Apartment's id: "+id_apartment));
	}

	@Override
	public Customer findCustomerByUsernameAndPasswordAndIsAvailableTrue(String username, String password) {
		return customerRepo.findCustomerByUsernameAndPasswordAndIsAvailableTrue(username, password).orElseThrow(() -> new UserNotFoundException("Customer's username and/or password invalid"));
	}

	@Override
	public Customer findCustomerByUsernameAndIsAvailableTrue(String username) {
		return customerRepo.findCustomerByUsernameAndIsAvailableTrue(username).orElseThrow(() -> new UserNotFoundException("Customer not found with username: "+username));
	}

	@Override
	public Customer findCustomerByTaxCodeAndIsAvailableTrue(String taxCode) {
		return customerRepo.findCustomerByTaxCodeAndIsAvailableTrue(taxCode).orElseThrow(() -> new UserNotFoundException("Customer not found with tax code: "+taxCode));
	}

	@Override
	public List<Customer> findAllCustomerByNameAndSurnameAndIsAvailableTrue(String name, String surname) {
		return customerRepo.findAllCustomerByNameAndSurnameAndIsAvailableTrue(name, surname);
	}

	@Override
	public Customer findCustomerByApartments_IdAndIsAvailableTrue(long apartment_id) {
		return customerRepo.findCustomerByApartments_IdAndIsAvailableTrue(apartment_id).orElseThrow(() -> new UserNotFoundException("Customer not found with apartment id: "+apartment_id));
	}

	@Override
	public Customer findByIdAndIsAvailableTrue(long idCustomer) {
		return customerRepo.findByIdAndIsAvailableTrue(idCustomer).orElseThrow(()-> new UserNotFoundException("No customer found with this id or unavailable"));
	}
}
