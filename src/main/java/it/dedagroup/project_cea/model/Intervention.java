package it.dedagroup.project_cea.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intervention {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private LocalDate date;
	@Column(nullable = false)
	private boolean isAvailable;
	@Column(nullable = false)
	private TypeOfIntervention type;
	@Column(nullable = false)
	private StatusIntervention status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Technician technician;
	@ManyToOne(fetch = FetchType.LAZY)
	private Apartment apartment;
	@ManyToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.PERSIST,  CascadeType.MERGE})
	Secretary secretary;
}
