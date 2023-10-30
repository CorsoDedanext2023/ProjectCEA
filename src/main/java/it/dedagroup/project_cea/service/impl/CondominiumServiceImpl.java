package it.dedagroup.project_cea.service.impl;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.repository.AdministratorRepository;
import it.dedagroup.project_cea.repository.ApartmentRepository;
import it.dedagroup.project_cea.repository.CondominiumRepository;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;

@Service
public class CondominiumServiceImpl implements CondominiumServiceDef {
	
	@Autowired
	CondominiumRepository condRepo;
	@Autowired
	ApartmentRepository apartmentRepository;
	@Autowired
	AdministratorRepository administratorRepository;

	@Override
	public Condominium findById(long id) {
		return condRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "no condominium found with this id"));
	}

	@Override

	public void addCondominium(Condominium condominium) {
		condRepo.save(condominium);
	}

	

	public Condominium findCondominiumByApartment_id(long apartmentId) {
		Apartment apartment=apartmentRepository.findById(apartmentId).orElseThrow(NotValidDataException::new);
		return condRepo.findById(apartment.getCondominium().getId()).orElseThrow(NotValidDataException::new);
	}

	public List<Condominium> getCondominiumByAdministrator_id(long administratorId){
		return condRepo.findAllByAdministrator_Id(administratorId);
	}
	
	//VISUALIZZAZIONE DEI CUSTOMER DATO UN ID CONDOMINIO E ORDINATI IN ORDINE ALFABETICO
	@Override
	public List<Customer> getCustomerByCondominiumId(long id_condominium) {
		return condRepo.findCustomersByCondominiumId(id_condominium).stream()
				.sorted((o1, o2) -> o2.getSurname().compareTo(o1.getSurname()))
				.collect(Collectors.toList());
	}

	@Override

	public Condominium updateCondominium(Condominium condominium) {
		return condRepo.save(condominium);
	}



	public Condominium findByIdAndIsAvailableTrue(long idCondominium) {
		return condRepo.findByIdAndIsAvailableTrue(idCondominium).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No condominium found with this id or unavailable"));
	}



}
