package com.project.web.ms.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "acceso")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Acceso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idaccesos;
	
	private String login;
	private String contrasenia;
	
	@Column(name="feacceso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date feacceso;
	
	private String feestado;
	
	@Column(name="fecreate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecreate;
	
	private String usucrea;

}
