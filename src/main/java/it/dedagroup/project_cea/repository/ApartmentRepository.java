package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
	public Optional<Apartment> findApartmentByIdAndIsAvailableTrue(long id_apartment);
	public Optional<Apartment> findApartmentByInterventionsIdAndIsAvailableTrue(long id_intervention);
	public Optional<Apartment> findApartmentByInterventionsId(long id_intervention);
	public Optional<Apartment> findApartmentByScansIdAndIsAvailableTrue(long id_meter);
	public Optional<Apartment> findApartmentByScansId(long id_meter);
	public Optional<Apartment> findApartmentByUnitNumberAndFloorNumberAndCondominiumIdAndIsAvailableTrue(int unit_number, int floor_number, long id_condominium);
	public Optional<Apartment> findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number, long id_condominium);
	public Optional<Apartment> findApartmentByCondominiumIdAndCustomerId(long id_condominium, long id_customer);
	public List<Apartment> findAllApartmentByCondominiumIdAndIsAvailableTrue(long id_condominium);
	public List<Apartment> findAllApartmentByCondominiumId(long id_condominium);
	public List<Apartment> findAllApartmentByCustomerIdAndIsAvailableTrue(long id_customer);
	public List<Apartment> findAllApartmentByCustomerId(long id_customer);

	public Optional<Apartment> findByIdAndIsAvailableTrue(long idApartment);

	//dovrebbe andarsi a prendere solamente la lista degli appartamenti abitati
	//public List<Apartment> findAllByCondominium_IdAndCustomerIsNotEmpty(long condominiumId);
}
