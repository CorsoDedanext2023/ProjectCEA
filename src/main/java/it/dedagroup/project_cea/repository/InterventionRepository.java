package it.dedagroup.project_cea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
	public List<Intervention> findAllByTechnician_ID(long idTechnician);
}
