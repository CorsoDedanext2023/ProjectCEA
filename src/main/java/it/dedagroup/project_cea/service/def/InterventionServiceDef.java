package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;

public interface InterventionServiceDef {
	
	public List<Intervention> findAllByType(TypeOfIntervention type);
	public Intervention findById(long idIntervention);
	public List<Intervention> findAll();

	void save(Intervention interv);
}
