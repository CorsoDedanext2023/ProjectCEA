package it.dedagroup.project_cea.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.repository.ApartmentRepository;
import it.dedagroup.project_cea.repository.BillRepository;
import it.dedagroup.project_cea.repository.CustomerRepository;
import it.dedagroup.project_cea.repository.InterventionRepository;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;

@Service
public class CustomerServiceImpl implements CustomerServiceDef{
	
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	InterventionRepository interventionRepo;
	@Autowired
	BillRepository billRepo;
	@Autowired
	ApartmentRepository apartmentRepo;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer modifyCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public void deleteCustomer(long customer_id) {
		customerRepo.deleteById(customer_id);
	}
	
	@Override
	public Intervention bookIntervention(long user_id, LocalDate interventionDate) {
		Apartment apartment=apartmentRepo.findApartmentByCustomer_id(user_id);
		Intervention intervention=new Intervention();
		intervention.setApartment(apartment);
		intervention.setStatus(StatusIntervention.PENDING);
		intervention.setType(TypeOfIntervention.FIXING_UP);
		return interventionRepo.save(intervention);
	}

	@Override
	public List<Bill> getBills(long user_id) {
		return billRepo.findAllBillByCustomer_id(user_id);
	}

	@Override
	public Bill payBill(long bill_id, LocalDate paymentDate) {
		Bill bill=billRepo.findById(bill_id).get();
		bill.setPaymentDay(paymentDate);
		return billRepo.save(bill);
		
	}

	@Override
	public Scan meterScan(long apartment_id, Bill lastBill) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer findCustomerById(long customer_id) {
		return customerRepo.findById(customer_id).get();
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerRepo.findAll();
	}

	@Override
	public Customer findCustomerByUsernameAndPassword(String username, String password) {
		return customerRepo.findCustomerByUsernameAndPassword(username, password).get();
	}

	@Override
	public Customer findCustomerByUsername(String username) {
		return customerRepo.findCustomerByUsername(username).get();
	}

	@Override
	public Customer findCustomerByTaxCode(String taxCode) {
		return customerRepo.findCustomerByTaxCode(taxCode).get();
	}

	@Override
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname) {
		return customerRepo.findAllCustomerByNameAndSurname(name, surname);
	}

	@Override
	public Customer findCustomerByApartments_Id(long apartment_id) {
		return customerRepo.findCustomerByApartments_Id(apartment_id).get();
	}
}
