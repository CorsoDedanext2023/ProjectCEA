package it.dedagroup.project_cea.exception.model;

import lombok.Getter;

@Getter
public class TechnicianInterventionLimitMetException extends RuntimeException {
	//Eccezione per quando viene raggiunto il limite giornaliero di interventi per tecnico
		private static final long serialVersionUID = 1L;
		private Object request;
		
		public TechnicianInterventionLimitMetException(Object o, String msg) {
			super(msg);
			request = o;
		}
		
		public TechnicianInterventionLimitMetException(String message, Throwable cause) {
			super(message, cause);
		}
		
		public TechnicianInterventionLimitMetException(String message) {
			super(message);
		}
		
	}

