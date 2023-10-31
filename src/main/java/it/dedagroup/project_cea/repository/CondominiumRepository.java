package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Customer;

public interface CondominiumRepository extends JpaRepository<Condominium, Long> {

    public List<Condominium> findAllByAdministrator_Id(long administratorId);
    public List<Condominium> findAllByAddressOrderByAddress(String address);
    
    @Query("SELECT c FROM Condominium co JOIN co.apartments a JOIN a.customer c WHERE co.id = :condominiumId")
    public List<Customer> findCustomersByCondominiumId(long condominiumId);

    public Optional<Condominium> findByIdAndIsAvailableTrue(long idCondominium);
}
