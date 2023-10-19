package it.dedagroup.project_cea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Scan;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScanRepository extends JpaRepository<Scan, Long>{
    public List<Scan> findAllByScanDate(LocalDate scanDate);
    public Optional<Scan> findByBill_id(long idBill);
    public Optional<Scan> findByApartment_id(long idApartment);
}
