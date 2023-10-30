package it.dedagroup.project_cea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.dedagroup.project_cea.model.Scan;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.List;

public interface ScanRepository extends JpaRepository<Scan, Long>{

    public List<Scan> findAllByApartment_Condominium_Id(long idCondominium);
    public List<Scan> findAllByScanDate(LocalDate scanDate);
    public Optional<Scan> findByBills_id(long idBill);
    public List<Scan> findAllByApartmentId(long idApartment);

}
