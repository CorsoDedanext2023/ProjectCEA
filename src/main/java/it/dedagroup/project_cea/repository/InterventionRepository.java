package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import it.dedagroup.project_cea.model.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
	public List<Intervention> findAllByTechnician_Id(long idTechnician);
	public List<Intervention> findAllByType(TypeOfIntervention type);
	public Optional<List<Intervention>> findAllByApartment_Customer_Id(long idCustomer);
/*
	@Query("SELECT t.interventions FROM Technician t " +
			"INNER JOIN t.interventions i " +
			"WHERE t.id = :technicianId " +
			"AND i.interventionDate = :date")
	List<Intervention> findInterventionsByTechnicianAndDate(@Param("technicianId") Long technicianId, @Param("date") LocalDate date);

 */
	public List<Intervention> findByTechnician_IdAndInterventionDate(long idTechnician, LocalDate date);
	public Optional<Intervention> findByIdAndIsAvailableTrue(long idIntervention);
}

