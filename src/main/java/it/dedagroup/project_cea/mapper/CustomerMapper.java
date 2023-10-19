package it.dedagroup.project_cea.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.dto.response.CustomerExtendedInfoDto;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;
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
		customer.setApartments(mapperApart.toListDto(c.getApartments()));
		customer.setAvailable(c.isAvailable());
		return customer;
	}
	
	public List<CustomerDto> toListDto(List<Customer> c){
		if(c == null || c.isEmpty())throw new NotValidDataException("List of customers is empty: "+c);
		return c.stream().map(this::toDto).toList();
	}
	
	public List<CustomerExtendedInfoDto> toCustomerExtendedinfo(Customer c) {
	    if (c == null) throw new NotValidDataException("Customer è vuoto");

	    List<CustomerExtendedInfoDto> lista = new ArrayList<>();

	    for (Apartment apartment : c.getApartments()) {
	        CustomerExtendedInfoDto dto = new CustomerExtendedInfoDto();
	        dto.setName(c.getName());
	        dto.setSurname(c.getSurname());
	        dto.setTaxCode(c.getTaxCode());
	        dto.setFloor(apartment.getFloorNumber());
	        dto.setUnitNumber(apartment.getUnitNumber());
	        lista.add(dto);
	    }
	    return lista;
	}
	
	public List<CustomerExtendedInfoDto> toListCustomersExtendedinfo(List<Customer> c) {
	    if (c == null || c.isEmpty()) throw new NotValidDataException("Lista dei customer è vuota");
	    return c.stream()
	            .flatMap(customer -> toCustomerExtendedinfo(customer).stream())
	            .collect(Collectors.toList());
	}
}
