package it.dedagroup.project_cea.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "unit_number", nullable = false)
	private int unitNumber;
	@Column(name = "floor_number", nullable = false)
	private int floorNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Customer customer;
	@OneToMany(mappedBy = "apartment")
	private List<Scan> scans;
	@ManyToOne(fetch = FetchType.LAZY)
	private Condominium condominium;
	@OneToMany(mappedBy = "apartment")
	private List<Intervention> interventions;
	@Column(nullable = false)
	private boolean isAvailable = true;
}
