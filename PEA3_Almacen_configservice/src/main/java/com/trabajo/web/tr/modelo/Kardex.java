package com.trabajo.web.tr.modelo;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "kardex")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Kardex {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idkardex;
	private String ingreso;
	private String salida ;

	//@NotNull(message = "producto no puede ser vacia")
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "idproducto")
		@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
		private Producto producto;
}
