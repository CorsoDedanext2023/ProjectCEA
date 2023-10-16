package it.dedagroup.project_cea.model;

public enum StatusIntervention {

	COMPLETED("COMPLETED"),
	POSTPONED("POSTPONED"),
	PENDING("PENDING"),
	ACCEPTED("ACCEPTED"),
	CANCEL("CANCEL");
	
	private String status;

	private StatusIntervention(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
}
