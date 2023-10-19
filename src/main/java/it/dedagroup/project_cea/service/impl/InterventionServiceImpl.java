package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.repository.ApartmentRepository;
import it.dedagroup.project_cea.repository.InterventionRepository;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;

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
		return null;
	}

	@Override
	public List<Intervention> findAllByType(TypeOfIntervention type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Intervention findById(long idIntervention) {
		return intervRepo.findById(idIntervention).orElse(null);
	}

}
