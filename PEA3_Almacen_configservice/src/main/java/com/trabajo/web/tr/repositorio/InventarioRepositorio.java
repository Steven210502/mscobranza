package com.trabajo.web.tr.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajo.web.tr.modelo.Producto;

import com.trabajo.web.tr.modelo.Inventario;
import com.trabajo.web.tr.modelo.Kardex;

@Repository
public interface InventarioRepositorio extends JpaRepository<Inventario, Long>{
	public List<Inventario> findByProducto(Producto producto) ;
	public List<Inventario> findByKardex(Kardex kardex) ;
	

}
