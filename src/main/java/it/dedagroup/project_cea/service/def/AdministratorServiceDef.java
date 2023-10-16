package it.dedagroup.project_cea.service.def;

import it.dedagroup.project_cea.model.Administrator;

public interface AdministratorServiceDef {
	
	void addAdministrator(Administrator administrator);
	void deleteAdministrator(long id);
	void modifyAdministrator(long id);
	Administrator findById(long id);

}
