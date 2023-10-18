package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Intervention;

public interface InterventionServiceDef {
		
	public Condominium findCondominiumByApartment_id(long idApartment);
	public List<Intervention> findAllByTechnicianId(long idTechnician);
	
}
