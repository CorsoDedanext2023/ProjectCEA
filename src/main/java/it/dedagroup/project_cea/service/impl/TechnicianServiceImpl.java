package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.exception.model.UserNotFoundException;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.repository.TechnicianRepository;
import it.dedagroup.project_cea.service.def.TechnicianServiceDef;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TechnicianServiceImpl implements TechnicianServiceDef{
	
	@Autowired
	TechnicianRepository techRepo;
	
	@Override
	public void save(Technician t) {
		techRepo.save(t);
		
	}

	@Override
	public Technician update(Technician t) {
		
		Technician tech = techRepo.findById(t.getId()).orElseThrow(()->new UserNotFoundException("Technician not found"));
		tech.setName(t.getName());
		tech.setSurname(t.getSurname());
		tech.setUsername(t.getUsername());
		tech.setPassword(t.getPassword());
		tech.setRole(t.getRole());
		techRepo.save(tech);
		return tech;
	}

	@Override
	public Technician findByInterventionId(long idIntervention) {
		techRepo.findByInterventions_Id(idIntervention).orElseThrow(()->new UserNotFoundException("Nessun tecnico trovato con ID intervento: " + idIntervention));
		return null;
	}

	@Override
	public List<Technician> findAllByInterventions_Secretary_id(long idSec) {
		return techRepo.findAllByInterventions_Secretary_id(idSec);
	}

	@Override
	public Technician findByNameAndSurname(String name, String surname) {
		return techRepo.findByNameAndSurname(name, surname).orElseThrow(()-> new UserNotFoundException("Technician not found with name " + name + " and surname " + surname));
	}

	@Override
	public Technician findById(long idTechnician) {
		return techRepo.findById(idTechnician).orElseThrow(()->new UserNotFoundException("Technician not found with ID: " + idTechnician));
	}

	@Override
	public Technician findByUsername(String username) {
		techRepo.findByUsername(username).orElseThrow(()->new UserNotFoundException("Technician not found with username: " +username));
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

	@Override
	public void remove(long id) {
		Technician t = techRepo.findById(id).orElseThrow(()->new UserNotFoundException("Technician not found with ID: " + id));
		t.setAvailable(false);
		techRepo.save(t);
	}

	@Override
	public void removeTechncianByUsername(String username) {
		Technician tech = techRepo.findByUsername(username).orElseThrow(()->new UserNotFoundException("Technician not found with username: " +username));
		tech.setAvailable(false);
		techRepo.save(tech);
	}


}
