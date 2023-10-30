package it.dedagroup.project_cea.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.dedagroup.project_cea.model.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
	
//	public Optional<Technician> findById(Long id);
	Optional<Technician> findByUsername(String username);
//	public List<Technician> findAll();
//	@Query("SELECT COUNT (*) FROM INTERVENTION i WHERE i.interventionDate  = :x")
//	public Optional<List<Technician>> findFreeTechnicians(LocalDate x);
	Optional<Technician> findByInterventions_Id(Long id);
	List<Technician> findAllByInterventions_Secretary_id(long idSec);
	Optional<Technician> findByNameAndSurnameAndIsAvailableTrue(String name, String surname);
	List<Technician> findAllByIsAvailableTrue();
	Optional<Technician> findByIdAndIsAvailableTrue(long idTechnician);
}