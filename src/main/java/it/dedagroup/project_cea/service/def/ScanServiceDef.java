package it.dedagroup.project_cea.service.def;

import java.util.List;
import java.util.Optional;

import it.dedagroup.project_cea.model.Scan;

public interface ScanServiceDef {
	public List<Scan> findAll();
	
	public void save(Scan scan);

	public void addScan(Scan scan, long idTechnician);

	public Scan findById(long id);

	public void removeScan(Scan scan);
	public void removeScanById(long id);

}
