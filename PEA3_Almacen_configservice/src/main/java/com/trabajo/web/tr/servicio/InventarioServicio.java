package com.trabajo.web.tr.servicio;
import java.util.List;


import com.trabajo.web.tr.modelo.*;
public interface InventarioServicio {
	
	
	public List<Inventario> ListAllInventary();
	public Inventario getInventary(Long id);
	
	public Inventario createInventario(Inventario inventario);
	public Inventario updateInventary(Inventario inventario);
	public Inventario deleteInventary(Long id);
	
	public List<Inventario> findByProducto(Producto producto);
	public List<Inventario> findByKardex(Kardex kardex);
	
}
