package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
	public Optional<Apartment> findApartmentByInterventionsId(long intervention_id);
	public Optional<Apartment> findApartmentByScanId(long meter_id);
	public Optional<Apartment> findApartmentByUnitNumberAndFloorNumberAndCondominiumId(int unit_number, int floor_number, int condominium_id);
	public List<Apartment> findAllApartmentByCondominiumId(long condominium_id);
	public List<Apartment> findAllApartmentByCustomerId(long customer_id);
	public Optional<Apartment> findApartmentByCustomer_id(long user_id);
}
