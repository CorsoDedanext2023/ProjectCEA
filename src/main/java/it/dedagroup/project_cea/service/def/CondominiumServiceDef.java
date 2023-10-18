package it.dedagroup.project_cea.service.def;



import it.dedagroup.project_cea.model.Condominium;

import java.util.Optional;

public interface CondominiumServiceDef {
	
	public Condominium findById(long id);

	public Condominium findCondominiumByApartment_id(long apartmentId);

}
