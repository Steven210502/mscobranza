package com.project.web.ms.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.web.ms.modelo.Deuda;
import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.Pago;
import com.project.web.ms.repositorio.PagoRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoServicioImplementa implements PagoServicio {
	
	public final PagoRepositorio pagoRepositorio;

	@Override
	public List<Pago> ListAllPago() {
		return pagoRepositorio.findAll();
	}

	@Override
	public Pago getPago(Long id) {	
		return pagoRepositorio.findById(id).orElse(null);
	}

	@Override
	public Pago createPago(Pago pago) {

		pago.setEstado("PAGADO");
		
		return pagoRepositorio.save(pago);
	}

	@Override
	public Pago updatePago(Pago pago) {
		Pago pagoUpdate = getPago(pago.getIdpago());
		
		if(pagoUpdate == null) {
			return null;
		}
		
		pagoUpdate.setEmpleado(pago.getEmpleado());
		pagoUpdate.setDeuda(pago.getDeuda());
		pagoUpdate.setNrocomprobante(pago.getNrocomprobante());
		pagoUpdate.setFechapago(pago.getFechapago());
		pagoUpdate.setTotalpagar(pago.getTotalpagar());
		pagoUpdate.setEstado(pago.getEstado());
		
		return pagoRepositorio.save(pagoUpdate);
	}

	@Override
	public Pago deletePago(Long id) {
		Pago pagoDelete = getPago(id);
		
		if(pagoDelete == null) {
			return null;
		}
		
		pagoDelete.setEstado("ELIMINADO");
		
		return pagoRepositorio.save(pagoDelete);
	}

	@Override
	public List<Pago> findByDeuda(Deuda deuda) {
		return pagoRepositorio.findByDeuda(deuda);
	}

	@Override
	public List<Pago> findByEmpleado(Empleado empleado) {
		return pagoRepositorio.findByEmpleado(empleado);
	}
	
	@Override
	public Pago updateTotal(Long id, Double quantity) {
		
		Pago PagoUpdateTotal = getPago(id);
		
		if(PagoUpdateTotal == null) {
			return null;
		}
		
		Double nro = PagoUpdateTotal.getTotalpagar() + quantity;
		
		PagoUpdateTotal.setTotalpagar(nro);
		
		return pagoRepositorio.save(PagoUpdateTotal);
	}

	
	

}
