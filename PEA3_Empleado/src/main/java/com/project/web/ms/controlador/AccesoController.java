package com.project.web.ms.controlador;

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
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.project.web.ms.modelo.Acceso;
import com.project.web.ms.modelo.Roles;
import com.project.web.ms.servicio.AccesoServicio;

@RestController
@RequestMapping(value = "/acceso")
public class AccesoController {

	@Autowired
	AccesoServicio accesoServicio;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Acceso>> ListarAcceso(@RequestParam(name = "accesoId",
	required = false) Long accesoId) {
		
		List<Acceso> accesos = new ArrayList<>();
		
		if(accesoId == null) {
			accesos = accesoServicio.ListAllAcceso();
			if(accesos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			accesos = accesoServicio.findByRoles(Roles.builder()
					.idrol(accesoId).build());
			if(accesos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(accesos);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Acceso> getAcceso(@PathVariable("id") Long id){
		
		Acceso accesos = accesoServicio.getAcceso(id);
		if(accesos == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(accesos);
	}
	
	//@RequestMapping(value = "/",method = RequestMethod.POST)
		@PostMapping
		public ResponseEntity<Acceso> CrearAcceso(@Valid @RequestBody Acceso acceso,BindingResult result){
			
			if(result.hasErrors()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
			}

			Acceso accesoCreado = accesoServicio.createAcceso(acceso);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(accesoCreado);
		}
		
		@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
		public ResponseEntity<Acceso> actualizarAcceso(@PathVariable("id") Long id,
				@RequestBody Acceso acceso){
			
			acceso.setIdaccesos(id);
			Acceso accesoEncontrado = accesoServicio.updateAcceso(acceso);
			
			if(accesoEncontrado == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(accesoEncontrado);
		}
		
		@DeleteMapping(value = "/eliminar/{id}")
		public ResponseEntity<Acceso> deleteAcceso(@PathVariable("id") Long id){
			Acceso accesoDelete = accesoServicio.deleteAcceso(id);
			
			if(accesoDelete == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(accesoDelete);
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
