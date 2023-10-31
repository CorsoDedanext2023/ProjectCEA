package it.dedagroup.project_cea.service.impl;

import java.time.LocalDate;
import java.util.List;

import it.dedagroup.project_cea.exception.model.EmptyListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.repository.ApartmentRepository;
import it.dedagroup.project_cea.repository.InterventionRepository;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InterventionServiceImpl implements InterventionServiceDef {

	@Autowired
	ApartmentRepository apartmentRepo;
	
	@Autowired
	CondominiumServiceDef condominiumServ;
	
	@Autowired
	InterventionRepository intervRepo;
	
	@Override
	public Condominium findCondominiumByApartment_id(long idApartment) {
		Apartment apartment = apartmentRepo.findById(idApartment).orElseThrow();
		Condominium condominium = apartment.getCondominium();
		return condominium;
	}

	@Override
	public List<Intervention> findAllByTechnicianId(long idTechnician) {
		return intervRepo.findAllByTechnician_Id(idTechnician);
	}

	@Override
	public List<Intervention> findAllByType(TypeOfIntervention type) {
		return intervRepo.findAllByType(type);
	}

	@Override
	public Intervention findById(long idIntervention) {
		return intervRepo.findById(idIntervention).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "no intervention found for the intervention id given"));
	}

	@Override
	public void save(Intervention interv) {
		intervRepo.save(interv);
	}

	@Override
	public List<Intervention> findAllByApartment_Customer_Id(long idCustomer) {
		return intervRepo.findAllByApartment_Customer_Id(idCustomer).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT, "No interventions found for this customer"));
	}

	@Override
	public List<Intervention> findByTechnician_IdAndInterventionDate(long idTechnician, LocalDate date) {
		return intervRepo.findByTechnician_IdAndInterventionDate(idTechnician, date);
	}

	@Override
	public Intervention findByIdAndIsAvailableTrue(long idIntervention) {
		return intervRepo.findByIdAndIsAvailableTrue(idIntervention)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No interventions found with this id or intervention is set to unavailable"));
	}

	@Override
	public List<Intervention> findAll() {
		return intervRepo.findAll();
	}

}
