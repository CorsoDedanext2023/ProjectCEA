package it.dedagroup.project_cea.service.def;

import java.util.List;
import it.dedagroup.project_cea.model.Apartment;

public interface ApartmentServiceDef {

	public void saveApartment(Apartment apartment);
	public void modifyApartment(Apartment apartment);
	public void deleteApartment(long id_apartment);
	
	public Apartment findById(long id_apartment);
	public List<Apartment> findAll();
	public Apartment findApartmentByInterventionsId(long id_intervention);
	public Apartment findApartmentByScansId(long id_meter);
	public Apartment findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number, long id_condominium);
	public List<Apartment> findAllApartmentByCondominiumId(long id_condominium);
	public List<Apartment> findAllApartmentByCustomerId(long id_customer);
	public Apartment findByIdAndIsAvailableTrue(long idApartment);
}
