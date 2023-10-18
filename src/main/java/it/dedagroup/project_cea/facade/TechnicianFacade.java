package it.dedagroup.project_cea.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.project_cea.businesslogic.ScanDtoRequest;
import it.dedagroup.project_cea.dto.request.InterventionRequest;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.mapper.InterventionMapper;
import it.dedagroup.project_cea.mapper.ScanMapper;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;
import it.dedagroup.project_cea.service.def.ScanServiceDef;
import it.dedagroup.project_cea.service.def.TechnicianServiceDef;

@Service
public class TechnicianFacade {

	@Autowired
	TechnicianServiceDef techServ;
	
	@Autowired
	ScanServiceDef scanServ;
	
	@Autowired
	InterventionServiceDef intervServ;
	
	@Autowired
	InterventionMapper intMapper;
	
	@Autowired
	ScanMapper scanMap;
	
	public List<ScanDTOResponse> getScans(){
		List<Scan> allScans = scanServ.findAll().stream().filter(sc -> sc.isAvailable() == true).toList();
		if(allScans.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "no scans found in database");
		}
		else {
			return scanMap.toScanDTOResponseList(allScans);
		}
	}
	
	public void setScanApartment(ScanDtoRequest scanRequest, InterventionRequest intRequest) {
//		Intervention interventionTech = new Intervention(intRequest.getInterventionDate(), intRequest.getType(), intRequest.getApartment());
		Intervention interventionTech = intervServ.findById(intRequest.getId());
		Scan scan=new Scan(scanRequest.getMcLiter(), scanRequest.getApartmentId(), scanRequest.getScanDate());
		scanServ.insertScan(scan);
	}
}
