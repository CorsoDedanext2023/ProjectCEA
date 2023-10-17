package it.dedagroup.project_cea.service.def;

import java.util.List;
import it.dedagroup.project_cea.model.Apartment;

public interface ApartmentServiceDef {
	public Apartment saveApartment(String username, Apartment apartment);
	public Apartment modifyApartment(Apartment apartment);
	public void deleteApartment(String username, Apartment apartment);
	
	public Apartment findApartmentByInterventionsId(long Intervention_id);
	public Apartment findApartmentByMeterId(long meter_id);
	public Apartment findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number, int condominium_id);
	public Apartment findAllApartmentByCondominiumId(long condominium_id);
	public List<Apartment> findAllApartmentByCustomerId(long customer_id);
}
