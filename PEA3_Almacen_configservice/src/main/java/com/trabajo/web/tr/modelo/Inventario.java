package com.trabajo.web.tr.modelo;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventarioalmacen")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idinventario;
	
	//@NotEmpty(message = "cantidad no debe ser vacÃ­o")
	private int cantidad;
	private int stock;
	
	
	private String status;
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	
	//@NotNull(message = "producto no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproducto")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Producto producto;
	
	//@NotNull(message = "kardex no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idkardex")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Kardex kardex;
	
	
	
	
}
