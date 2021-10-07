package com.trabajo.web.tr.modelo;
import java.util.Date;

import javax.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproducto;
	private String codigo;
	private String nombre;
	private String precioventa;
	private Integer stock;
	private String descripcion;
	private String imagen;
	private Integer estado;
	
}
