package it.dedagroup.project_cea.service.def;



import java.util.List;

import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Customer;

public interface CondominiumServiceDef {
	
	public Condominium findById(long id);
	void addCondominium(Condominium condominium);
	Condominium updateCondominium(Condominium c);
	public Condominium findCondominiumByApartment_id(long apartmentId);
	List<Customer> getCustomerByCondominiumId(long id_condominium);
	List<Condominium> getCondominiumByAdministrator_id(long id_condominium);
	

	public Condominium findByIdAndIsAvailableTrue(long idCondominium);
}
