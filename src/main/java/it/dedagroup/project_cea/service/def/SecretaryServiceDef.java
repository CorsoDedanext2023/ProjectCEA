package it.dedagroup.project_cea.service.def;

import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.repository.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import java.util.Optional;

public interface SecretaryServiceDef {
    public List<Secretary> findAllByIntervention_Technician_IdAndIsAvailableTrue(long idTechnician);
    public Secretary findById(long idSecretary);
    public Secretary findByIdAndIsAvailableTrue(long idSecretary);
}
