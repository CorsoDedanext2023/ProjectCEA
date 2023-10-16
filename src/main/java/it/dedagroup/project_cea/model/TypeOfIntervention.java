package it.dedagroup.project_cea.model;

public enum TypeOfIntervention {
	
	METER_READING("METER READING"),
	FIXING_UP("FIXING UP");

	
	private String type;

	private TypeOfIntervention(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	
	
}
