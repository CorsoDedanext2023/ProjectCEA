package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Customer;

@Component
public class CustomerMapper {
	@Autowired
	ApartmentMapper mapperApart;
	public CustomerDto toDto(Customer c) {
		if (c==null)throw new NotValidDataException("Customer is empty: "+c);
		CustomerDto customer = new CustomerDto();
		customer.setName(c.getName());
		customer.setSurname(c.getSurname());
		customer.setUsername(c.getUsername());
		customer.setTaxCode(c.getTaxCode());
		customer.setRole(c.getRole());
		customer.setAvailable(c.isAvailable());
		return customer;
	}
	
	public List<CustomerDto> toListDto(List<Customer> c){
		if(c == null || c.isEmpty())throw new NotValidDataException("List of customers is empty: "+c);
		return c.stream().map(this::toDto).toList();
	}
}
