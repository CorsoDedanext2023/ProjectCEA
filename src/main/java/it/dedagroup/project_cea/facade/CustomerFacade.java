package it.dedagroup.project_cea.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;
@Service
public class CustomerFacade {
	@Autowired
	CustomerServiceDef customerServiceDef;
	
	public CustomerDto saveCustomer(Customer customer) {
		return null;
	}
	public CustomerDto modifyCustomer(Customer customer){
		return null;
	}
	public void deleteCustomer(long customer_id){

	}
	
	public CustomerDto findCustomerById(long customer_id){
		return null;
	}
	public CustomerDto findAllCustomer(){
		return null;
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
		return null;
	}
	public CustomerDto findCustomerByApartments_Id(long apartment_id){
		return null;
	}
}
