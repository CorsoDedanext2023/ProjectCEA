package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.dedagroup.project_cea.model.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
	
//	public Optional<Technician> findById(Long id);
	public Optional<Technician> findByUsername(String username);
//	public List<Technician> findAll();
//	@Query("SELECT t FROM Technician t WHERE t.max_intervention_for_technician < :n")
//	public Optional<List<Technician>> findFreeTechnicians(int n);
	public Optional<Technician> findByInterventions_Id(Long id);
}