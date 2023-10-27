package it.dedagroup.project_cea.facade;

import java.util.List;

import it.dedagroup.project_cea.exception.model.NotValidDataException;
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

	public List<ScanDTOResponse> getAllScans(){
		List<Scan> allScans = scanServ.findAll().stream().filter(sc -> sc.isAvailable() == true).toList();
		if(allScans.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "no scans found in database");
		}
		else {
			return scanMap.toScanDTOResponseList(allScans);
		}
	}


	public TechnicianDTOResponse update(TechnicianDTORequest request) {
		techServ.findById(request.getId());
		Technician newTech = techServ.update(techMapper.toTechnicianFromDtoRequest(request));
		return techMapper.toTechnicianDTOResponse(newTech);
	}

	public TechnicianDTOResponse findByInterventionId(long idIntervention) {
		if(idIntervention<1) {
			throw new NotValidDataException("Intervention ID must be positive");
		} else {
			return techMapper.toTechnicianDTOResponse(techServ.findByInterventionId(idIntervention));
		}
	}

	public TechnicianDTOResponse findById(long idTech ) {
		if(idTech<1){
			throw new NotValidDataException("ID must be a positive number");
		}
		return techMapper.toTechnicianDTOResponse(techServ.findById(idTech));
	}

	public TechnicianDTOResponse findByUsername(String username) {
		if(username.isBlank()){
			throw new NotValidDataException("Username cannot be blank");
		}
		return techMapper.toTechnicianDTOResponse(techServ.findByUsername(username));
	}

	public List<TechnicianDTOResponse> findAll(){
		List<Technician> list = techServ.findAll();
		if(list.isEmpty()) {
			throw new UserNotFoundException("Empty list of technicians");
		}
		return techMapper.technicianDTOResponsesList(list);
	}

	public String removeById(TechnicianDTORequest request) {
		techServ.removeById(request.getId());
		return "Technico Rimosso";
	}

	public String removeByUsername(TechnicianDTORequest request) {
		techServ.removeByUsername(request.getUsername());
		return "Technico Rimosso";
	}
	
	public List<TechnicianDTOResponse> findFree(){
		List<Technician> list = techServ.findFree();
		if(list.isEmpty()) {
			throw new UserNotFoundException("Empty list of technicians");
		}
		return techMapper.technicianDTOResponsesList(list);
	}

}
