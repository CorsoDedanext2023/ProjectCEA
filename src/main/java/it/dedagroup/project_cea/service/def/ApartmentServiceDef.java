package it.dedagroup.project_cea.service.def;

import java.util.List;
import java.util.Optional;

import it.dedagroup.project_cea.model.Apartment;

public interface ApartmentServiceDef {

	public void saveApartment(Apartment apartment);
	public void modifyApartment(Apartment apartment);
	public void deleteApartment(long id_apartment);

	public Apartment findApartmentByIdAndIsAvailableTrue(long id_apartment);
	List<Apartment> findAllAndIsAvailableTrue();
	public Apartment findById(long id_apartment);
	public List<Apartment> findAll();
	public Apartment findApartmentByCondominiumIdAndCustomerId(long id_condominium, long id_customer);
	public Apartment findApartmentByInterventionsId(long id_intervention);
	public Apartment findApartmentByScansId(long id_meter);
	public Apartment findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number, long id_condominium);
	public List<Apartment> findAllApartmentByCondominiumId(long id_condominium);
	public List<Apartment> findAllApartmentByCustomerId(long id_customer);

	public Apartment findApartmentByInterventionsIdAndIsAvailableTrue(long id_intervention);
	public Apartment findApartmentByScansIdAndIsAvailableTrue(long id_meter);
	public Apartment findApartmentByUnitNumberAndFloorNumberAndCondominiumIdAndIsAvailableTrue(int unit_number, int floor_number, long id_condominium);
	public List<Apartment> findAllApartmentByCondominiumIdAndIsAvailableTrue(long id_condominium);
	public List<Apartment> findAllApartmentByCustomerIdAndIsAvailableTrue(long id_customer);
}
