package com.project.web.ms.servicio;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.web.ms.modelo.Acceso;
import com.project.web.ms.modelo.Roles;
import com.project.web.ms.repositorio.AccesoRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccesoServicioImp implements AccesoServicio {

	public final AccesoRepositorio accesoRepositorio;
	
	@Override
	public List<Acceso> ListAllAcceso() {
		return accesoRepositorio.findAll();
	}

	@Override
	public Acceso getAcceso(Long id) {
		return accesoRepositorio.findById(id).orElse(null);
	}

	@Override
	public Acceso createAcceso(Acceso acceso) {
		acceso.setLogin("admin"); 
		acceso.setContrasenia("123");
		acceso.setFeacceso(new Date());
		acceso.setFeestado("1");
		acceso.setFecreate(new Date());
		acceso.setUsucrea("1");
		return accesoRepositorio.save(acceso);
	}

	@Override
	public Acceso updateAcceso(Acceso acceso) {
		Acceso accesoUpdate = getAcceso(acceso.getIdaccesos());
		
		if(accesoUpdate == null) {
			return null;
		}
		//accesoUpdate.setRoles (acceso.getRoles());
		accesoUpdate.setLogin(acceso.getLogin());
		accesoUpdate.setContrasenia(acceso.getContrasenia());
		accesoUpdate.setFeacceso(acceso.getFeacceso());
		accesoUpdate.setFeestado(acceso.getFeestado());
		accesoUpdate.setFecreate(acceso.getFecreate());
		accesoUpdate.setUsucrea(acceso.getUsucrea());
		
		return accesoRepositorio.save(accesoUpdate);
	}

	@Override
	public Acceso deleteAcceso(Long id) {
		Acceso accesoDelete = getAcceso(id);
		
		if(accesoDelete == null) {
			return null;
		}
		
		accesoDelete.setLogin("ELIMINADO");
		accesoDelete.setContrasenia("0");
		accesoDelete.setFeestado ("0");
		accesoDelete.setUsucrea("0");
		
		return accesoRepositorio.save(accesoDelete);
	}

	@Override
	public List<Acceso> findByRoles(Roles rol) {
		return accesoRepositorio.findByRoles(rol);
	}

}
