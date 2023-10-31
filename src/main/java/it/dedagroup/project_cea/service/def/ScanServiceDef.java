package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Scan;

public interface ScanServiceDef {
	public List<Scan> findAll();
	
	public void save(Scan scan);

	public Scan findById(long id);

	void addScan(Scan scan, long idTechnician);

	public void removeScan(Scan scan);
	public void removeScanById(long id);

	public List<Scan> findAllScanByCondominiumId(long condominiumId);

	public List<Scan> findAllScanByApartmentId(long apartmentId);

}
