package it.dedagroup.project_cea.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Technician extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false,columnDefinition = "int default 5")
    private int maxWorkload=5;
	@Column(nullable = false)
    private int workload;
	@Column(nullable = false)
	private Role role = Role.TECHNICIAN;
	private boolean isAvailable = true;
	
	@OneToMany(mappedBy = "technician")
	private List<Intervention> interventions;


	public int getMaxWorkload() {
		return maxWorkload;
	}

}

