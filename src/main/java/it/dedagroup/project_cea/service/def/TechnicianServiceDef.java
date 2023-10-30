package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Technician;

public interface TechnicianServiceDef {

	void save(Technician t);
	Technician update(Technician t);

	//	boolean removeTechnician (TechnicianRequest request);
	public void removeById(long id);
	void removeByUsername(String username);

	Technician findById(long idTechnician);
	Technician findByUsername(String username);
	List<Technician> findAll();
	List<Technician> findFree();
	Technician findByInterventionId(long idIntervention);
	List<Technician> findAllByInterventions_Secretary_id(long idSec);
	Technician findByNameAndSurnameAndIsAvailableTrue(String name, String surname);
	List<Technician> findAllByIsAvailableTrue();
	Technician findByIdAndIsAvailableTrue(long idTechnician);
}
