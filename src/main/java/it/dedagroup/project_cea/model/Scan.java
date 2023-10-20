package it.dedagroup.project_cea.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
//TODO Implementare la relazione con Condominium
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private Double mcLiter;
	@Column(nullable = false)
	private boolean isAvailable = true;
	@Column(nullable = false)
	private LocalDate scanDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Apartment apartment;
	@OneToMany(mappedBy = "scan")
	private List<Bill> bills;

}
