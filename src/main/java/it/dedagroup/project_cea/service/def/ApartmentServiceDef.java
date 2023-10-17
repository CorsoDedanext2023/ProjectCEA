package it.dedagroup.project_cea.service.def;

import java.util.List;
import it.dedagroup.project_cea.model.Apartment;

public interface ApartmentServiceDef {
	public Apartment saveApartment(String username, Apartment apartment);
	public Apartment modifyApartment(Apartment apartment);
	public void deleteApartment(String username, Apartment apartment);
	
	public Apartment findById(long apartment_id);
	public List<Apartment> findAll();
	public Apartment findApartmentByInterventionsId(long intervention_id);
	public Apartment findApartmentByMeterId(long meter_id);
	public Apartment findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number, int condominium_id);
	public List<Apartment> findAllApartmentByCondominiumId(long condominium_id);
	public List<Apartment> findAllApartmentByCustomerId(long customer_id);
}
