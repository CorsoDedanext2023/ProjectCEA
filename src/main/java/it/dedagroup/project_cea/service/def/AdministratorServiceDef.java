package it.dedagroup.project_cea.service.def;

import java.util.Optional;

import it.dedagroup.project_cea.model.Administrator;

public interface AdministratorServiceDef {
	
	Administrator addAdministrator(Administrator administrator);
	void deleteAdministrator(long id);
	public Administrator updateAdministrator(Administrator administrator);
	Optional<Administrator> findById(long id);
	
}
