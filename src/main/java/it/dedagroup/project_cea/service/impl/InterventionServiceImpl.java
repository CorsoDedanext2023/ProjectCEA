package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.repository.InterventionRepository;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InterventionServiceImpl implements InterventionServiceDef {
	
	@Autowired
	InterventionRepository intervRepo;

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
	public List<Intervention> findAll() {
		return intervRepo.findAll();
	}

}
