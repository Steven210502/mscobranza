package com.project.web.ms.servicio;

import java.util.List;

import com.project.web.ms.modelo.Deuda;
import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.Pago;


public interface PagoServicio {
	
	public List<Pago> ListAllPago();
	public Pago getPago(Long id);
	
	public Pago createPago(Pago pago);
	public Pago updatePago(Pago pago);
	public Pago deletePago(Long id);
	
	public List<Pago> findByDeuda(Deuda deuda);
	public List<Pago> findByEmpleado(Empleado empleado);
	
	public Pago updateTotal(Long id, Double quantity);
		
	
	
}
