package it.dedagroup.project_cea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Scan;

import java.util.List;

public interface ScanRepository extends JpaRepository<Scan, Long>{

    public List<Scan> findAllByApartment_Condominium_Id(long idCondominium);
}
