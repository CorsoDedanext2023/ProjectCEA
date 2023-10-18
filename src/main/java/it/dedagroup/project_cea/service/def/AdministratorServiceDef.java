package it.dedagroup.project_cea.service.def;



import it.dedagroup.project_cea.model.Administrator;

public interface AdministratorServiceDef {
	
	Administrator addAdministrator(Administrator administrator);
	void deleteAdministrator(long id);
	public Administrator updateAdministrator(Administrator administrator);
	Administrator findById(long id);
	public Administrator findByCondominiums_Id(long id);
}
