package it.dedagroup.project_cea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Scan;

public interface ScanRepository extends JpaRepository<Scan, Long>{
	
}
