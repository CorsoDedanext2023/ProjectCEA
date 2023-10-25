package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

	public Optional<Apartment> findApartmentByInterventionsId(long id_intervention);
	public Optional<Apartment> findApartmentByScansId(long id_meter);
	public Optional<Apartment> findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number, long id_condominium);
	public List<Apartment> findAllApartmentByCondominiumId(long id_condominium);
	public List<Apartment> findAllApartmentByCustomerId(long id_customer);
	public Optional<Apartment> findApartmentByCustomer_id(long id_user);

	//dovrebbe andarsi a prendere solamente la lista degli appartamenti abitati
	//public List<Apartment> findAllByCondominium_IdAndCustomerIsNotEmpty(long condominiumId);
}
