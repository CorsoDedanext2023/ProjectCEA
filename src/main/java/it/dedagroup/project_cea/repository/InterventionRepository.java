package it.dedagroup.project_cea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
	
	public List<Intervention> findAllByType(TypeOfIntervention type);
	public List<Intervention> findAllByStatus(String status);
}
