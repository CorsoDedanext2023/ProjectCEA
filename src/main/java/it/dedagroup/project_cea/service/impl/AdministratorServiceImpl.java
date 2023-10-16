package it.dedagroup.project_cea.service.impl;

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
		AdministratorRepository.delete(null);
	}

	@Override
	public void modifyAdministrator(long id) {
		
	}

	@Override
	public Administrator findById(long id) {
		
		return null;
	}

}
