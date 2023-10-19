package it.dedagroup.project_cea.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Bill;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    public List<Bill> findAllBillByDeliveringDay(LocalDate deliveringDate);
    public List<Bill> findAllBillByScan_Apartment_Customer_Id (long customerId);
}
