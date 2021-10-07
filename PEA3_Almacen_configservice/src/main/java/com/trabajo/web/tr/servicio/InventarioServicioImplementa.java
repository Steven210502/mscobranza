package com.trabajo.web.tr.servicio;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


import com.trabajo.web.tr.modelo.Inventario;
import com.trabajo.web.tr.modelo.Kardex;
import com.trabajo.web.tr.modelo.Producto;

import com.trabajo.web.tr.repositorio.InventarioRepositorio;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioServicioImplementa implements InventarioServicio {
	public final InventarioRepositorio inventarioRepositorio;
	
	

	@Override
	public List<Inventario> ListAllInventary() {
		return inventarioRepositorio.findAll();
	}
	

	@Override
	public Inventario getInventary(Long id) {
		return inventarioRepositorio.findById(id).orElse(null);
	}
	@Override
	public Inventario createInventario(Inventario inventario) {

		inventario.setStatus("CREADO");
		inventario.setCreateAt(new Date());
		return inventarioRepositorio.save(inventario);
	}  
	
	
	
	
	@Override
	public Inventario updateInventary(Inventario inventario) {
		Inventario inventarioUpdate = getInventary(inventario.getIdinventario());
		
		if(inventarioUpdate == null) {
			return null;
		}
		
		inventarioUpdate.setCantidad(inventario.getCantidad());
		inventarioUpdate.setStock(inventario.getStock());
		inventarioUpdate.setStatus(inventario.getStatus());
		inventarioUpdate.setCreateAt(inventario.getCreateAt());
		inventarioUpdate.setProducto(inventario.getProducto());
		inventarioUpdate.setKardex(inventario.getKardex());
		return inventarioRepositorio.save(inventarioUpdate);
	}

	

	@Override
	public Inventario deleteInventary(Long id) {
		Inventario inventarioDelete = getInventary(id);
		
		if(inventarioDelete == null) {
			return null;
		}
		
		
		inventarioDelete.setCantidad(0);
		inventarioDelete.setStock(0);
		inventarioDelete.setStatus("ELIMINADO");
		return inventarioRepositorio.save(inventarioDelete);
	}


	@Override
	public List<Inventario> findByProducto(Producto producto) {
		return inventarioRepositorio.findByProducto(producto);
	
	}


	@Override
	public List<Inventario> findByKardex(Kardex kardex) {
		return inventarioRepositorio.findByKardex(kardex);
	}
	
}
