package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.repository.ApartmentRepository;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;

@Service
public class ApartmentServiceImpl implements ApartmentServiceDef {
	@Autowired
	ApartmentRepository repo; 
	
	@Override
	public Apartment saveApartment(String username, Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment modifyApartment(Apartment apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteApartment(String username, Apartment apartment) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Apartment findById(long apartment_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Apartment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment findApartmentByInterventionsId(long Intervention_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment findApartmentByMeterId(long meter_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number,
			int condominium_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment findAllApartmentByCondominiumId(long condominium_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Apartment> findAllApartmentByCustomerId(long customer_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
