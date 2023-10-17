package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.TechnicianRequest;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.repository.TechnicianRepository;
import it.dedagroup.project_cea.service.def.TechnicianServiceDef;

@Service
public class TechnicianServiceImpl implements TechnicianServiceDef{
	
	@Autowired
	TechnicianRepository techRepo;
	
	@Override
	public void save(Technician t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Technician update(Technician t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeTechnician(TechnicianRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Technician findByIntervention(long idIntervention) {
		//aggiungere orElseThrow
		techRepo.findByIntervention_Id(idIntervention);
		return null;
	}

	@Override
	public Technician findById(long idTechnician) {
		//aggiungere orElseThrow
		techRepo.findById(idTechnician);
		return null;
	}

	@Override
	public Technician findByUsername(String username) {
		//aggiungere orElseThrow
		techRepo.findByUsername(username);
		return null;
	}

	@Override
	public List<Technician> findAll() {
		return techRepo.findAll();
	}

	@Override
	public List<Technician> findFree() {
		// TODO Auto-generated method stub
		return null;
	}


}
