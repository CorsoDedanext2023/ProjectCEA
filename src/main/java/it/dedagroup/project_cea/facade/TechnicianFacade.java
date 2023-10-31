package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.List;

import it.dedagroup.project_cea.dto.request.*;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.StatusIntervention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

	/**
	 * Questo metodo ci permette di vedere tutte le letture presenti sul database che non sono state cancellate
	 * @return Ritorna una la lista di {@link ScanDtoRequest}, ovvero una lista di ogetti derivanti dalla classe {@link Scan}
	 * con dati adattati alla risposta.<br>
	 * Questa lista corrisponde ad un elenco di letture effettuate presenti sul database che non sono state ancora cancellate.
	 */

	public List<ScanDTOResponse> getAllScans(){
		List<Scan> allScans = scanServ.findAll().stream().filter(sc -> sc.isAvailable() == true).toList();
		if(allScans.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "no scans found in database");
		}
		else {
			return scanMap.toScanDTOResponseList(allScans);
		}
	}

	/**
	 * @param request richiede in input un JSON contenente un oggetto di tipo {@link TechnicianDTORequest}
	 * @return ritorna un oggetto di tipo  {@link TechnicianDTOResponse}, ovvero un oggetto derivante dal model {@link Technician}
	 * 			con dati adattati alla risposta.
	 */
	public TechnicianDTOResponse update(TechnicianDTORequest request) {
		techServ.findById(request.getId());
		Technician newTech = techServ.update(techMapper.toTechnicianFromDtoRequest(request));
		return techMapper.toTechnicianDTOResponse(newTech);
	}

	/**
	 * Con questo metodo andiamo a cercare il tecnico che è assegato ad un intervento in base all'ID di quest ultimo.
	 * @param idIntervention richiede in input, tramite parametro, un numero corrispondente all'ID dell'intervento
	 * @return ritorna un oggetto di tipo {@link TechnicianDTOResponse}ovvero un oggetto derivante dal model {@link Technician}
	 * 			con dati adattati alla risposta.
	 */
	public TechnicianDTOResponse findByInterventionId(long idIntervention) {
		if(idIntervention<1) {
			throw new NotValidDataException("Intervention ID must be positive");
		} else {
			return techMapper.toTechnicianDTOResponse(techServ.findByInterventionId(idIntervention));
		}
	}

	/**
	 * Questo metodo ci permette di cercare un tecnico tramite il suo ID.
	 * @param idTech richiede in input, tramite parametro, un numero corrispondente all'ID del tecnico.
	 * @return ritorna un oggetto di tipo {@link TechnicianDTOResponse} ovvero un oggetto derivante dal model {@link Technician}
	 * 	 * 			con dati adattati alla risposta.
	 */
	public TechnicianDTOResponse findById(long idTech ) {
		if(idTech<1){
			throw new NotValidDataException("ID must be a positive number");
		}
		return techMapper.toTechnicianDTOResponse(techServ.findById(idTech));
	}

	/**
	 * Questo metodo ci permette di cercare un tecnico tramite il suo username.
	 * @param username richiede in input, tramite parametro, una stringa corrispondente all'username col quale si è registrato il tecnico.
	 * @return ritorna un oggetto di tipo {@link TechnicianDTOResponse} ovvero un oggetto derivante dal model {@link Technician}
	 *			con dati adattati alla risposta.
	 */
	public TechnicianDTOResponse findByUsername(String username) {
		if(username==null || username.isBlank()){
			throw new NotValidDataException("Username cannot be blank");
		}
		return techMapper.toTechnicianDTOResponse(techServ.findByUsername(username));
	}

	/**
	 * Questo metodo ci permette di trovare tutti i tecnici presenti sul nostro database.
	 * @return Ritorna una List di {@link TechnicianDTOResponse}, ovvero una lista di oggetti derivanti dalla classe {@link Technician}
	 * 			con dati adattati alla risposta.
	 */
	public List<TechnicianDTOResponse> findAll(){
		List<Technician> list = techServ.findAll();
		if(list.isEmpty()) {
			throw new UserNotFoundException("Empty list of technicians");
		}
		return techMapper.technicianDTOResponsesList(list);
	}

	/**
	 * Questo metodo simula la cancellazione di un tecnico, tramite il suo ID, dal nostro database evitanto la cancellazione effettiva
	 * ma semplicemente settando la variabile "isAvailable" dell'oggetto {@link Technician} a false.
	 * @param idTechnician richiede in input, tramite parametro, un numero corrispondente all'ID del tecnico.
	 * @return Ritorna una stringa in caso di "cancellazione" avvenuta con successo.
	 */
	public String removeById(long idTechnician) {
		if(idTechnician<1){
			throw new NotValidDataException("ID must be a positive number");
		}
		Technician t = techServ.findById(idTechnician);
		techServ.removeById(idTechnician);
		return "Technico Rimosso";
	}

	/**
	 * Questo metodo simula la cancellazione di un tecnico, tramite il suo username, dal nostro database evitanto la cancellazione effettiva
	 * ma semplicemente settando la variabile "isAvailable" dell'oggetto {@link Technician} a false.
	 * @param username richiede in input, tramite parametro, una stringa corrispondente all'username col quale si è registrato il tecnico.
	 * @return Ritorna una stringa in caso di "cancellazione" avvenuta con successo.
	 */
	public String removeByUsername(String username) {
		if(username==null || username.isBlank()){
			throw new NotValidDataException("Username cannot be blank");
		}
		Technician t = techServ.findByUsername(username);
		techServ.removeByUsername(username);
		return "Technico Rimosso";
	}
	
	public List<TechnicianDTOResponse> findFree(){
		List<Technician> list = techServ.findFree();
		if(list.isEmpty()) {
			throw new UserNotFoundException("Empty list of technicians");
		}
		return techMapper.technicianDTOResponsesList(list);
	}

	/**
	 * Questo metodo ci permette di inserire una lettura, collegata ad un intervento ed effettuata in un appartamento, da parte del Tecnico.<br>
	 * In caso di lettura effettuata con successo, cambia lo {@link StatusIntervention} dell'intervento a "Completed".
	 * @param request Prende in input un oggetto di tipo {@link ScanDtoRequest} che al suo interno
	 *                contiene tutti i dati necessari al completamento dell'inserimento.
	 * @return Ritorna una stringa in caso di inserimento corretto.
	 * @throws ResponseStatusException Ritorna un messaggio personalizzato in base a quale tipo di dato non è corretto.
	 */
	public String addScan(ScanDtoRequest request) {
		Intervention i = intervServ.findById(request.getIdIntervention());				//se non trova in intervento in questo id ritorna un exception
		if(intervServ.findAllByTechnicianId(request.getIdTechnician()).contains(i)){ 	//controlliamo se l'intervento è effettivamente di quel tecnico
			if(request.getIdApartment()==i.getApartment().getId()){						//controlliamo che l'intervento sia sull'appartamento giusto
				if(request.getScanDate().isEqual(i.getInterventionDate())){						//controlliamo che la data sia corretta
					Scan newScan = scanMap.toScanFromDtoRequest(request);
					scanServ.save(newScan);
					i.setStatus(StatusIntervention.COMPLETED);
					intervServ.save(i);
					return "Scan added";
				}else{
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Intervantion date is not valid.");
				}
			}else{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Apartement's ID is wrong.");
			}
		}else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no intervention found for the intervention id given");
		}
	}

	/**
	 * In questo metodo controlliamo che tutti i dati ricevuti in input tramite {@link InterventionRebookDTORequest} siano validi
	 * e solo in quel caso settiamo la nuova data dell'intervento.
	 * @param request Richiede in input un oggetto di tipo {@link InterventionRebookDTORequest} che al suo interno
	 *                contiene tutti i dati necessari al completamento dell'inserimento.
	 * @return Ritorna i dati dell'intervento modificato convertito in oggetto di tipo {@link InterventionDTOResponse},
	 * 			un oggetto derivante dal model {@link Intervention} con dati adattati alla risposta.
	 * @throws ResponseStatusException Ritorna un messaggio di errore personalizzato in base a quale tipo di dato non è corretto.
	 */
	public InterventionDTOResponse rebookIntervention(InterventionRebookDTORequest request) {
		Intervention i = intervServ.findById(request.getIdIntervention());
		List<Intervention> listInterv = intervServ.findAllByTechnicianId(request.getIdTechnician());
		LocalDate interventionDate = request.getInterventionDate();
		LocalDate postponedDate = request.getPostponedDate();

		if(listInterv!= null && listInterv.contains(i)) {
			if (request.getIdApartment() == i.getApartment().getId()) {
				if (interventionDate.isEqual(i.getInterventionDate())) {
					if (request.getType() == i.getType()) {
						if (i.getStatus() == StatusIntervention.ACCEPTED) {
							i.setInterventionDate(request.getPostponedDate());
							intervServ.save(i);
							return intMapper.toInterventionDTOResponse(i);
						} else {
							throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This intervention was not accepted");
						}
					} else {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The type of this intervention is not " + i.getType().toString());
					}
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This intervention was not booked for today");
				}
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This ID apartment is not referred to intervention ID " + i.getId());
			}
		}else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This intervention was not assigned at this technician");
		}
	}
}
