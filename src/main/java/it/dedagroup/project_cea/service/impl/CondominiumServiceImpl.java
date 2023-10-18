package it.dedagroup.project_cea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.repository.CondominiumRepository;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;

@Service
public class CondominiumServiceImpl implements CondominiumServiceDef {
	
	@Autowired
	CondominiumRepository condRepo;

	@Override
	public Condominium findById(long id) {
		return condRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "no condominium found with this id"));
	}

	@Override
	public void addCondominium(Condominium condominium) {
		condRepo.save(condominium);
	}

	
}
