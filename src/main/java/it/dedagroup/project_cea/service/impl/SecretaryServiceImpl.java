package it.dedagroup.project_cea.service.impl;

import it.dedagroup.project_cea.exception.model.UserNotFoundException;
import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.repository.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.service.def.SecretaryServiceDef;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SecretaryServiceImpl implements SecretaryServiceDef {
    @Autowired
    SecretaryRepository secRepo;

    @Override
    public List<Secretary> findAllByIntervention_Technician_IdAndIsAvailableTrue(long idTechnician) {
        return secRepo.findAllByIntervention_Technician_IdAndIsAvailableTrue(idTechnician);
    }

    @Override
    public Secretary findById(long idSecretary) {
        return secRepo.findById(idSecretary).orElseThrow(()-> new UserNotFoundException("No secretary found with id " + idSecretary));
    }

    @Override
    public Secretary findByIdAndIsAvailableTrue(long idSecretary) {
        return secRepo.findByIdAndIsAvailableTrue(idSecretary).orElseThrow(()-> new UserNotFoundException("No secretary found with id " + idSecretary + " or secretary unavailable"));
    }
}
