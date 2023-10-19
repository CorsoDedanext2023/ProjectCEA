package it.dedagroup.project_cea.service.def;



import java.util.List;

import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Customer;

public interface CondominiumServiceDef {
	
	public Condominium findById(long id);
	void addCondominium(Condominium condominium);

	public Condominium findCondominiumByApartment_id(long apartmentId);
	List<Customer> getConsumersByCondominiumId(long id_condominium);

}
