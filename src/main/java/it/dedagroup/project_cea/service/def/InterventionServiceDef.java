package it.dedagroup.project_cea.service.def;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	public List<Intervention> findAllByApartment_Customer_Id(long idCustomer);
	List<Intervention> findByTechnician_IdAndInterventionDate(long idTechnician, LocalDate date);
	Intervention findByIdAndIsAvailableTrue(long idIntervention);
}
