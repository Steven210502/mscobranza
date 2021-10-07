package com.project.web.ms.modelo;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pago")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpago;
	
	//@NotEmpty(message = "El nroComprobante no debe ser vacÃ­o")
	private int nrocomprobante;
	
	//@Positive(message = "El totalpagar debe ser mayor que cero")
	private Double totalpagar;
	private String estado;

	@Column(name="fechapago")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechapago;
	
	//@NotNull(message = "La Deuda no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "iddeuda")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Deuda deuda;
	
	//@NotNull(message = "El empleado no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idempleado")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Empleado empleado;

}
