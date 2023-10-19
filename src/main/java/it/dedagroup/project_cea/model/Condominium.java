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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO Implementare la relazione con la lettura (Scan)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condominium {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String address;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Administrator administrator;
	@OneToMany(mappedBy = "condominium")
	private List<Apartment> apartments;
	@Column(nullable = false)
	private boolean isAvailable = true;
}
