package it.dedagroup.project_cea.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Administrator;
import it.dedagroup.project_cea.repository.AdministratorRepository;
import it.dedagroup.project_cea.service.def.AdministratorServiceDef;

@Service
public class AdministratorServiceImpl implements AdministratorServiceDef{
	
	@Autowired
	private AdministratorRepository AdministratorRepository;
	

	@Override
	public void addAdministrator(Administrator administrator) {
		AdministratorRepository.save(administrator);
	}

	@Override
	public void deleteAdministrator(long id) {
		AdministratorRepository.deleteById(id);
	}

	
	@Override
	public void updateAdministrator(long id) {
		//TODO perchè update??
		return;
	}

	@Override
	public Optional<Administrator> findById(long id) {
		return AdministratorRepository.findById(id);
	}
	
	
}
