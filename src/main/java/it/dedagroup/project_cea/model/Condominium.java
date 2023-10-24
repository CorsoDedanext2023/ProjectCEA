package it.dedagroup.project_cea.model;

import java.util.List;

import jakarta.persistence.*;
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
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JoinColumn
	private Administrator administrator;
	@OneToMany(mappedBy = "condominium",cascade = CascadeType.PERSIST)
	private List<Apartment> apartments;
	@Column(nullable = false)
	private boolean isAvailable = true;
}
