package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;

public interface InterventionServiceDef {
	
	public Intervention findById(Long idIntervention);
	public List<Intervention> findAll();
	public List<Intervention>findAllByStatus(String status);
	public List<Intervention> findAllByType(TypeOfIntervention type);
	
}
