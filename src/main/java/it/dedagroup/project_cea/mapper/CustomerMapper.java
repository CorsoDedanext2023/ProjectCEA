package it.dedagroup.project_cea.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.dedagroup.project_cea.dto.request.AddCustomerDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.CustomerDtoResponse;
import it.dedagroup.project_cea.dto.response.CustomerExtendedInfoDTOResponse;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Customer;

@Component
public class CustomerMapper {
	@Autowired
	ApartmentMapper mapperApart;
	public CustomerDtoResponse toDto(Customer c) {
		if (c==null)throw new NotValidDataException("Customer is empty: "+c);
		CustomerDtoResponse customer = new CustomerDtoResponse();
		customer.setName(c.getName());
		customer.setSurname(c.getSurname());
		customer.setUsername(c.getUsername());
		customer.setTaxCode(c.getTaxCode());
		customer.setRole(c.getRole());
		customer.setAvailable(c.isAvailable());
		return customer;
	}
	
	public List<CustomerDtoResponse> toListDto(List<Customer> c){
		if(c == null || c.isEmpty())throw new NotValidDataException("List of customers is empty: "+c);
		return c.stream().map(this::toDto).toList();
	}

	public Customer fromAddCustomerDTORequestToCustomer(AddCustomerDtoRequest request){
		//TODO Da discutere l'aggiunta dell'appartamento in customer
		//Apartment a = apartmentServiceDef.findById(request.getId_apartment());
		Customer customer = new Customer();
		//List<Apartment> apartments = new ArrayList<>();
		//apartments.add(a);
		customer.setName(request.getName());
		customer.setSurname(request.getSurname());
		customer.setUsername(request.getUsername());
		customer.setPassword(request.getPassword());
		customer.setTaxCode(request.getTaxCode());
		//customerAdd.setApartments(apartments);
		return customer;
	}
	
	public List<CustomerExtendedInfoDTOResponse> toCustomerExtendedinfo(Customer c) {
	    if (c == null) throw new NotValidDataException("Customer è vuoto");

	    List<CustomerExtendedInfoDTOResponse> lista = new ArrayList<>();

	    for (Apartment apartment : c.getApartments()) {
	        CustomerExtendedInfoDTOResponse dto = new CustomerExtendedInfoDTOResponse();
	        dto.setName(c.getName());
	        dto.setSurname(c.getSurname());
	        dto.setTaxCode(c.getTaxCode());
	        dto.setFloor(apartment.getFloorNumber());
	        dto.setUnitNumber(apartment.getUnitNumber());
	        lista.add(dto);
	    }
	    return lista;
	}
	
	public List<CustomerExtendedInfoDTOResponse> toListCustomersExtendedinfo(List<Customer> c) {
	    if (c == null || c.isEmpty()) throw new NotValidDataException("Lista dei customer è vuota");
	    return c.stream()
	    		.filter(customer -> customer.isAvailable()==true)
	            .flatMap(customerDto -> toCustomerExtendedinfo(customerDto).stream())
	            .collect(Collectors.toList());
	}
}
