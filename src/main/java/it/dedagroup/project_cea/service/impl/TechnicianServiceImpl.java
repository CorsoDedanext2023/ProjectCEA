package it.dedagroup.project_cea.service.impl;

import java.util.List;

import it.dedagroup.project_cea.exception.model.EmptyListException;
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
		return techRepo.findByInterventions_Id(idIntervention).orElseThrow(()->new UserNotFoundException("Nessun tecnico trovato con ID intervento: " + idIntervention));
	}

	@Override
	public List<Technician> findAllByInterventions_Secretary_id(long idSec) {
		return techRepo.findAllByInterventions_Secretary_id(idSec);
	}

	@Override
	public Technician findByNameAndSurnameAndIsAvailableTrue(String name, String surname) {
		return techRepo.findByNameAndSurnameAndIsAvailableTrue(name, surname).orElseThrow(()-> new UserNotFoundException("Technician not found with name " + name + " and surname " + surname));
	}

	@Override
	public List<Technician> findAllByIsAvailableTrue() {
		return techRepo.findAllByIsAvailableTrue();
	}

	@Override
	public Technician findByIdAndIsAvailableTrue(long idTechnician) {
		return techRepo.findByIdAndIsAvailableTrue(idTechnician)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No technicians found with this id or technician is set to unavailable"));
	}

	@Override
	public Technician findById(long idTechnician) {
		return techRepo.findById(idTechnician)
				.orElseThrow(()->new UserNotFoundException("Technician not found with ID: " + idTechnician));
	}

	@Override
	public Technician findByUsername(String username) {
		return techRepo.findByUsername(username)
				.orElseThrow(()->new UserNotFoundException("Technician not found with username: " +username));
	}

	@Override
	public List<Technician> findAll() {
		return techRepo.findAll();
	}

	@Override
	public List<Technician> findFree() {
		// TODO Metodo che ritorni una lista di tecnici disponibili(che non hanno fatto più di 5 interventi in quel giorno)
		List<Technician> lista = techRepo.findAll();
		for(Technician t : lista) {
			if(t.isAvailable()) {
				lista.add(t);
				return lista;
			}
		}
		return null;
	}

	@Override
	public void removeById(long id) {
		Technician t = techRepo.findById(id)
				.orElseThrow(()->new UserNotFoundException("Technician not found with ID: " + id));
		t.setAvailable(false);
		techRepo.save(t);
	}

	@Override
	public void removeByUsername(String username) {
		Technician tech = techRepo.findByUsername(username)
				.orElseThrow(()->new UserNotFoundException("Technician not found with username: " +username));
		tech.setAvailable(false);
		techRepo.save(tech);
	}


}
