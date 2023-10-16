package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.dto.request.TechnicianRequest;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.model.User;

public interface TechnicianService {
	
	Technician addTechnician (User user, List<Intervention> interventions, Secretary secretary );
	
	boolean removeTechnician (TechnicianRequest request);
	
	void save(Technician t);
	
	List<Technician> findBySecretary(long idSecretary);
	List<Technician> findByIntervention(long idIntervention);
	
	List<Technician> findById(long idTechnician);
	List<Technician> findAll();
}
