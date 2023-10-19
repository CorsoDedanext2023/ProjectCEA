package it.dedagroup.project_cea.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.project_cea.dto.request.ScanDtoRequest;
import it.dedagroup.project_cea.dto.request.TechnicianDTORequest;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.dto.response.TechnicianDTOResponse;
import it.dedagroup.project_cea.exception.model.UserNotFoundException;
import it.dedagroup.project_cea.mapper.InterventionMapper;
import it.dedagroup.project_cea.mapper.ScanMapper;
import it.dedagroup.project_cea.mapper.TechnicianMapper;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;
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
	ApartmentServiceDef apartmentServ;
	
	@Autowired
	CondominiumServiceDef condominiumServ;
	
	@Autowired
	InterventionMapper intMapper;
	
	@Autowired
	TechnicianMapper techMapper;
	
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
	
	public ScanDTOResponse addScanApartment(ScanDtoRequest scanRequest, long idIntervention) {
		Intervention interventionTech = intervServ.findById(idIntervention);
		Scan newScan = scanMap.toScanFromDtoRequest(scanRequest);
		return scanMap.toScanDTOResponse(newScan);
	}
	
	public TechnicianDTOResponse update(TechnicianDTORequest request) {
		techServ.findById(request.getId());
		Technician newTech = techServ.update(techMapper.toTechnicianFromDto(request));
		return techMapper.toDTO(newTech);
	}
	
	public Technician findByInterventionId(TechnicianDTORequest request) {
		if(request.getInterventions()==null) {
			throw new UserNotFoundException(request, "Non esistono Tecnici per questo Intervento");
		} else {
			Technician tech = techServ.findByInterventionId(request.getId());
			return tech;
		}
		
	}
	
	public Technician findById(TechnicianDTORequest request ) {
		if(request.getId()==0) {
			throw new UserNotFoundException(request, "Non esistono Tecnici con questo ID");
		} else {
			Technician tech = techServ.findByInterventionId(request.getId());
			return tech;
		}
	}
	
	public TechnicianDTOResponse findByUsername(TechnicianDTORequest request) {
		if(request.getUsername()==null) {
			throw new UserNotFoundException(request, "Non esistono Tecnici con questa Username");
		} else {
			Technician tech = techServ.findByInterventionId(request.getId());
			return techMapper.toDTO(tech);
		}
	}
	
	public List<Technician> findAll(){
		List<Technician> lista = techServ.findAll();
		if(lista.isEmpty()) {
			throw new UserNotFoundException("La Lista di Tecnici è vuota");
		}
		return lista;
	}
	
	public String removeTechnicianById(TechnicianDTORequest request) {
		Technician t = techServ.findById(request.getId());
		if(t==null) {
			throw new UserNotFoundException("Non esistono Tecnici con questo ID");
		}else {
			techServ.remove(t.getId());
			return "Technico Rimosso";
		}
	}
	
	public String removeTechnicianByUsername(TechnicianDTORequest request) {
		Technician t = techServ.findByUsername(request.getUsername());
		if(t==null) {
			throw new UserNotFoundException("Non esistono Tecnici con questo Username");
		}else {
			techServ.remove(t.getId());
			return "Technico Rimosso";
		}
	}
	
}
