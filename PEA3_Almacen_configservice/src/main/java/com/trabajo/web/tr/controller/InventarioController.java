package com.trabajo.web.tr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajo.web.tr.controller.ErrorMessage;
import com.trabajo.web.tr.modelo.*;

import com.trabajo.web.tr.servicio.InventarioServicio;


@RestController
@RequestMapping(value = "/inventario")
public class InventarioController {
	
	
	@Autowired
	InventarioServicio inventarioServicio;
	
	//@GetMapping("/")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Inventario>> ListarInventario(@RequestParam(name = "productoId",
	required = false) Long productoId)  {
		
		List<Inventario> inventarios = new ArrayList<>();
		
		if(productoId == null) {
			inventarios = inventarioServicio.ListAllInventary();
			if(inventarios.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			inventarios = inventarioServicio.findByProducto(Producto.builder()
					.idproducto(productoId).build());
			if(inventarios.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(inventarios);
	}

	@PostMapping
	public ResponseEntity<Inventario> CrearInventario(@Valid @RequestBody Inventario inventario,BindingResult result){
		
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
		}

		Inventario inventarioCreado = inventarioServicio.createInventario(inventario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(inventarioCreado);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Inventario> actualizarInventario(@PathVariable("id") Long id,
			@RequestBody Inventario inventario){
		
		inventario.setIdinventario(id);
		Inventario inventarioEncontrado = inventarioServicio.updateInventary(inventario);
		
		if(inventarioEncontrado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(inventarioEncontrado);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Inventario> getInventario(@PathVariable("id") Long id){
		
		Inventario inventario = inventarioServicio.getInventary(id);
		if(inventario == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(inventario);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<Inventario> deleteInventary(@PathVariable("id") Long id){
		Inventario inventarioDelete = inventarioServicio.deleteInventary(id);
		
		if(inventarioDelete == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(inventarioDelete);
	}
	
	 private String formatMessage( BindingResult result){
	        List<Map<String,String>> errors = result.getFieldErrors().stream()
	                .map(err ->{
	                    Map<String,String>  error =  new HashMap<>();
	                    error.put(err.getField(), err.getDefaultMessage());
	                    return error;

	                }).collect(Collectors.toList());
	        ErrorMessage errorMessage = ErrorMessage.builder()
	                .code("01")
	                .messages(errors).build();
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonString="";
	        try {
	            jsonString = mapper.writeValueAsString(errorMessage);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return jsonString;
	    }	
}
