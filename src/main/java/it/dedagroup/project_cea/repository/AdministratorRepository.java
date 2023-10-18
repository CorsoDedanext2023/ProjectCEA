package it.dedagroup.project_cea.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
	
}
