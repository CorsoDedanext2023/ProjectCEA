package it.dedagroup.project_cea.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.repository.ScanRepository;
import it.dedagroup.project_cea.service.def.ScanServiceDef;

@Service
public class ScanServiceImpl implements ScanServiceDef {
	
	@Autowired
	ScanRepository scanRepo;

	@Override
	public List<Scan> findAll() {
		return scanRepo.findAll();
	}

	@Override
	public void insertScan(Scan scan) {
		scanRepo.save(scan);
	}
	
	@Override
	public Scan findById(long id) {
		return scanRepo.findById(id).orElseThrow(() -> new NotValidDataException("Lettura non trovata"));
	}

}
