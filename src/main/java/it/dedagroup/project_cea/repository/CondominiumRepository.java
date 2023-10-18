package it.dedagroup.project_cea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Condominium;

import java.util.List;
import java.util.Optional;

public interface CondominiumRepository extends JpaRepository<Condominium, Long> {

    public List<Condominium> findAllByAdministrator_Id(long administratorId);
    public List<Condominium> findAllByAddressOrderByAddress(String address);





}
