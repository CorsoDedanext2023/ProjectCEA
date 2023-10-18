package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.repository.ApartmentRepository;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;

@Service
public class ApartmentServiceImpl implements ApartmentServiceDef {
	@Autowired
	ApartmentRepository repo; 
	
	@Override
	public Apartment saveApartment(String username, Apartment apartment) {
		return repo.save(apartment);
	}

	@Override
	public Apartment modifyApartment(Apartment apartment) {
		Apartment apartmodify = findById(apartment.getId());
		apartmodify.setUnitNumber(apartment.getUnitNumber());
		apartmodify.setFloorNumber(apartment.getFloorNumber());
		apartmodify.setCustomer(apartment.getCustomer());
		apartmodify.setScans(apartment.getScans());
		apartmodify.setCondominium(apartment.getCondominium());
		apartmodify.setInterventions(apartment.getInterventions());
		apartmodify.setAvailable(apartment.isAvailable());
		return apartmodify;
	}

	@Override
	public void deleteApartment(String username, long id_apartment) {
		Apartment apartdelete = findById(id_apartment);
		apartdelete.setAvailable(false);
		repo.save(apartdelete);
	}
	
	@Override
	public Apartment findById(long id_apartment) {
		return repo.findById(id_apartment).orElseThrow(() -> new NotValidDataException("Apartment not found with id: "+id_apartment));
	}

	@Override
	public List<Apartment> findAll() {
		return repo.findAll();
	}

	@Override
	public Apartment findApartmentByInterventionsId(long id_intervention) {
		return repo.findApartmentByInterventionsId(id_intervention).orElseThrow(() -> new NotValidDataException("Apartment not found with interventions id: "+id_intervention));
	}

	@Override
	public Apartment findApartmentByMeterId(long id_meter) {
		return repo.findApartmentByMeterId(id_meter).orElseThrow(() -> new NotValidDataException("Apartment not found with meter id: "+id_meter));
	}

	@Override
	public Apartment findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number,
			long id_condominium) {
		return repo.findApartmentByUnitNumberAndFloorNumberAndCondominiumId(unit_number, floor_number, id_condominium).orElseThrow(()-> new NotValidDataException("Apartment not found with: unit number, floor number and condominium id"));
	}

	@Override
	public List<Apartment> findAllApartmentByCondominiumId(long id_condominium) {
		return repo.findAllApartmentByCondominiumId(id_condominium);
	}

	@Override
	public List<Apartment> findAllApartmentByCustomerId(long id_customer) {
		return repo.findAllApartmentByCustomerId(id_customer);
	}
}
