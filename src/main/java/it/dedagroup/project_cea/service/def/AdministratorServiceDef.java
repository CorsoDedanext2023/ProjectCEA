package it.dedagroup.project_cea.service.def;

import java.util.Optional;

import it.dedagroup.project_cea.model.Administrator;

public interface AdministratorServiceDef {
	
	void addAdministrator(Administrator administrator);
	void deleteAdministrator(long id);
	void updateAdministrator(long id);
	Optional<Administrator> findById(long id);
	
}
