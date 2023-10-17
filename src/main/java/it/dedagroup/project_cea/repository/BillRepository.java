package it.dedagroup.project_cea.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
