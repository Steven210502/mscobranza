package com.project.web.ms.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deuda")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Deuda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iddeuda;
	private int monto;
	private int cuota;
	private String descuento;
}
