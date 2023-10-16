package it.dedagroup.project_cea.mapper;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.RegisterUserDto;
import it.dedagroup.project_cea.model.Administrator;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Role;
import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.model.User;

@Component
public class UserMapper {
	
	public User toUser(RegisterUserDto user) {
		if(user.getRole().equals(Role.ADMIN)) {
			Administrator admin = new Administrator();
			admin.setName(user.getName());
			admin.setPassword(user.getPassword());
			admin.setSurname(user.getSurname());
			admin.setUsername(user.getUsername());
			return admin;
		} 
		else if(user.getRole().equals(Role.SECRETARY)) {
			Secretary secretary = new Secretary();
			secretary.setName(user.getName());
			secretary.setPassword(user.getPassword());
			secretary.setSurname(user.getSurname());
			secretary.setUsername(user.getUsername());
			return secretary;
		}
		else if(user.getRole().equals(Role.CUSTOMER)) {
			Customer customer = new Customer();
			customer.setName(user.getName());
			customer.setPassword(user.getPassword());
			customer.setSurname(user.getUsername());
			customer.setUsername(user.getSurname());
			customer.setTaxCode(user.getTaxCode());
			return customer;
		}
		else if(user.getRole().equals(Role.SECRETARY)) {
			Technician tech = new Technician();
			tech.setName(user.getName());
			tech.setPassword(user.getPassword());
			tech.setSurname(user.getSurname());
			tech.setUsername(user.getUsername());
			return tech;
		}
		return null;
		
	}

}
