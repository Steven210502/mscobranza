package com.project.web.ms.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.web.ms.modelo.Deuda;
import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.Pago;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Long>{
	
	public List<Pago> findByDeuda(Deuda deuda);
	public List<Pago> findByEmpleado(Empleado empleado);
}
