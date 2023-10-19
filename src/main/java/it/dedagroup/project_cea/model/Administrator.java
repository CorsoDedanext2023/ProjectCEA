package it.dedagroup.project_cea.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Administrator extends User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@JsonIgnore
	@OneToMany(mappedBy = "administrator")
	private List<Condominium> condominiums;
	@Column(nullable = false)
	private Role role = Role.ADMINISTRATOR;
	private boolean isAvailable = true;
	
}
