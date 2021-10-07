package com.project.web.ms.servicio;

import java.util.List;

import com.project.web.ms.modelo.Acceso;
import com.project.web.ms.modelo.Roles;

public interface AccesoServicio {

	public List<Acceso> ListAllAcceso();
	public Acceso getAcceso(Long id);
	
	public Acceso createAcceso(Acceso acceso);
	public Acceso updateAcceso(Acceso acceso);
	public Acceso deleteAcceso(Long id);
	
	public List<Acceso> findByRoles(Roles rol);
	/*public List<Pago> findByEmpleado(Empleado emp);*/
	
	
}
