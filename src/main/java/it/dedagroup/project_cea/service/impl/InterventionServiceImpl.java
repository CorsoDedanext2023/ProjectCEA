package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.repository.InterventionRepository;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;

@Service
public class InterventionServiceImpl implements InterventionServiceDef {
	
	@Autowired
	InterventionRepository intervRepo;

	@Override
	public List<Intervention> findAllByType(TypeOfIntervention type) {
		return intervRepo.findAllByType(type);
	}

	@Override
	public Intervention findById(Long idIntervention) {
		//TODO aggiungere orElseThrow
		intervRepo.findById(idIntervention);
		return null;
	}

	@Override
	public List<Intervention> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Intervention> findAllByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
