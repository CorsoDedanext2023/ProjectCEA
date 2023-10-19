package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;

public interface InterventionServiceDef {
		
	public Condominium findCondominiumByApartment_id(long idApartment);
	public List<Intervention> findAllByTechnicianId(long idTechnician);
	public List<Intervention> findAllByType(TypeOfIntervention type);
	public Intervention findById(long idIntervention);
	public List<Intervention> findAll();
	void save(Intervention interv);
}
