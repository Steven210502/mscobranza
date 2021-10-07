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
@Table (name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pedido {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idpedido;
	private Integer cantidad; 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproducto")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Producto producto;
	
}
