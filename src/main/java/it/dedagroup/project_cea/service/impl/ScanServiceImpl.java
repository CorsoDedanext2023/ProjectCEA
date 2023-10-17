package it.dedagroup.project_cea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
