package it.dedagroup.project_cea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import it.dedagroup.project_cea.model.Secretary;

import java.util.Optional;

public interface SecretaryRepository extends JpaRepository<Secretary, Long> {
    public List<Secretary> findAllByIntervention_Technician_IdAndIsAvailableTrue(long idTechnician);
    public Optional<Secretary> findByIdAndIsAvailableTrue(long idSecretary);
}
