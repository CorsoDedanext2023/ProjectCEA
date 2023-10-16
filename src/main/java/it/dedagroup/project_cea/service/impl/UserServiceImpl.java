package it.dedagroup.project_cea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Administrator;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.model.User;
import it.dedagroup.project_cea.repository.AdministratorRepository;
import it.dedagroup.project_cea.repository.CustomerRepository;
import it.dedagroup.project_cea.repository.SecretaryRepository;
import it.dedagroup.project_cea.repository.TechnicianRepository;
import it.dedagroup.project_cea.service.def.UserServiceDef;

@Service
public class UserServiceImpl implements UserServiceDef{
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private SecretaryRepository secretaryRepository;
	
	@Autowired
	private TechnicianRepository technicianRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void addUser(User user) {
		if(user instanceof Secretary) secretaryRepository.save((Secretary)user);
		else if(user instanceof Administrator) administratorRepository.save((Administrator)user);
		else if(user instanceof Technician) technicianRepository.save((Technician)user);
		else if(user instanceof Customer) customerRepository.save((Customer)user);
	}

}
