package it.dedagroup.project_cea.service.impl;

import java.util.List;
import it.dedagroup.project_cea.exception.model.ScanNotFoundException;
import it.dedagroup.project_cea.exception.model.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.repository.ScanRepository;
import it.dedagroup.project_cea.repository.TechnicianRepository;
import it.dedagroup.project_cea.service.def.ScanServiceDef;

@Service
public class ScanServiceImpl implements ScanServiceDef {

	@Autowired
	ScanRepository scanRepo;
	
	@Autowired
	TechnicianRepository techRepo; 

	@Override
	public Scan findById(long id) {
		return scanRepo.findById(id).orElseThrow(() -> new NotValidDataException("Scan not foud"));
	}

	@Override
	public List<Scan> findAll() {
		return scanRepo.findAll();
	}
	
	@Override
	public void save(Scan scan) {
		scanRepo.save(scan);
	}

	@Override
	public void removeScan(Scan scan) {
		scanRepo.findById(scan.getId()).orElseThrow(()->new ScanNotFoundException("Scan not found"));
		scan.setAvailable(false);
		scanRepo.save(scan);
	}

	@Override
	public void removeScanById(long id) {
		Scan sc = scanRepo.findById(id).orElseThrow(()->new ScanNotFoundException("Scan not found with ID: " + id));
		sc.setAvailable(false);
		scanRepo.save(sc);
	}
	@Override
	public List<Scan> findAllScanByCondominiumId(long condominiumId) {
		return scanRepo.findAllByApartment_Condominium_Id(condominiumId);
	}

	@Override
	public List<Scan> findAllScanByApartmentId(long apartmentId) {
		return scanRepo.findAllByApartmentId(apartmentId);
	}
}
