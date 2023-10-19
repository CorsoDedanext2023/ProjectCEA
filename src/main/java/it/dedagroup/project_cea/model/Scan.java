package it.dedagroup.project_cea.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
	private double mcLiter = 0;
	@Column(nullable = false)
	private boolean isAvailable = true;
    @Column(nullable = false)
    private LocalDate scanDate;

	@OneToOne(mappedBy = "meter")
	private Apartment apartment;
	@OneToMany(mappedBy = "meter")
	private List<Bill> bills;

}
