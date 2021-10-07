package com.trabajo.web.tr.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "movimientoalmacen")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Movimiento {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idmovimiento;
	private Integer cantidad; 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpedido")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idinventario")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Inventario inventario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idempleado")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Empleado  empleado;
	
}
