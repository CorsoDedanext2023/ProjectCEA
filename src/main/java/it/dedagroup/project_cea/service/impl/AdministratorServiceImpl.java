package it.dedagroup.project_cea.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Administrator;
import it.dedagroup.project_cea.repository.AdministratorRepository;
import it.dedagroup.project_cea.service.def.AdministratorServiceDef;

@Service
public class AdministratorServiceImpl implements AdministratorServiceDef{
	
	@Autowired
	private AdministratorRepository administratorRepository;

//ci dovrò inserire un eccezione migliore
	@Override
	public Administrator findAdministratorById(long administrator_id) {
		return administratorRepository.findById(administrator_id).orElseThrow(RuntimeException::new);
	}

	@Override
	public Administrator addAdministrator(Administrator administrator) {
		return administratorRepository.save(administrator);
	}

	@Override
	public void deleteAdministrator(long id) {
		administratorRepository.deleteById(id);
	}

	
	@Override
	public Administrator updateAdministrator(Administrator administrator) {
		return administratorRepository.save(administrator);
	}

	@Override
	public Administrator findById(long id) {
		return administratorRepository.findById(id).orElseThrow(()-> new RuntimeException("Non esiste nessun amministratore con questo id"));
	}

	@Override
	public Administrator findByCondominiums_Id(long id) {
		return administratorRepository.findByCondominiums_Id(id).orElseThrow(()-> new RuntimeException("Non esiste nessun condominio con questo id"));
	}
	
	
}
