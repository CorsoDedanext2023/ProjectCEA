package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.AddCustomerDto;
import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.mapper.CustomerMapper;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;
@Service
public class CustomerFacade {
	@Autowired
	CustomerServiceDef customerServiceDef;
	@Autowired
	CustomerMapper customerMapper;
	//TODO CustomerFacade da implementare + controlli
	public void saveCustomer(AddCustomerDto request) {
		try {
			customerServiceDef.findCustomerByUsername(request.getUsername());
		} catch (NotValidDataException e) {
			Customer customerAdd = new Customer();
			customerAdd.setName(request.getName());
			customerAdd.setSurname(request.getSurname());
			customerAdd.setUsername(request.getSurname());
			customerAdd.setPassword(request.getPassword());
			customerAdd.setTaxCode(request.getTaxCode());
			customerServiceDef.saveCustomer(customerAdd);
		}
		throw new NotValidDataException("Error existing user with username: "+request.getUsername());
	}
	public void modifyCustomer(Customer customer){
	}
	
	public void deleteCustomer(long id_customer){

	}
	
	public Intervention bookIntervention(long id_user, LocalDate interventionDate) {
		return null;
	}

	public List<Bill> getBills(long id_user) {
		return null;
	}

	public Bill payBill(long id_bill, LocalDate paymentDate) {
		return null;
	}

	public Scan meterScan(long id_apartment, Bill lastBill) {
		return null;
	}
	
	public CustomerDto findCustomerById(long id_customer){
		if(id_customer < 0)throw new NotValidDataException("Error insert a valid customer id: "+id_customer);
		return customerMapper.toDto(customerServiceDef.findCustomerById(id_customer));
	}
	public List<CustomerDto> findAllCustomer(){
		return customerMapper.toListDto(customerServiceDef.findAllCustomer());
	}
	public CustomerDto findCustomerByUsernameAndPassword(String username, String password){
		return null;
	}
	public CustomerDto findCustomerByUsername(String username){
		return null;
	}
	public CustomerDto findCustomerByTax_Code(String taxCode){
		return null;
	}
	public List<CustomerDto> findAllCustomerByNameAndSurname(String name, String surname){
		if (name == null || name.isEmpty())throw new NotValidDataException("Insert a value on field name");
		if (surname == null || surname.isEmpty())throw new NotValidDataException("Insert a value on field surname");
		if (!name.matches("^[\\p{L} '-]+$"))throw new NotValidDataException("Insert a valid name: "+name);
		if (!surname.matches("^[\\p{L} '-]+$"))throw new NotValidDataException("Insert a valid surname: "+surname);
		return customerMapper.toListDto(customerServiceDef.findAllCustomerByNameAndSurname(name, surname));
	}
	public CustomerDto findCustomerByApartments_Id(long id_apartment){
		if (id_apartment < 0)throw new NotValidDataException("Error insert a valid apartment id: "+id_apartment);
		return customerMapper.toDto(customerServiceDef.findCustomerByApartments_Id(id_apartment));
	}
}
