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
import com.project.web.ms.modelo.Roles;
import com.project.web.ms.modelo.Usuario;
import com.project.web.ms.servicio.UsuarioServicio;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioServicio usuarioServicio;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> ListarUsuario(@RequestParam(name = "usuarioId",
	required = false) Long usuarioId) {
		
		List<Usuario> usuario = new ArrayList<>();
		
		if(usuarioId == null) {
			usuario = usuarioServicio.ListAllUsuario();
			if(usuario.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			usuario = usuarioServicio.findByRoles(Roles.builder()
					.idrol(usuarioId).build());
			if(usuario.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id){
		
		Usuario usuario = usuarioServicio.getUsuario(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> CrearUsuario(@Valid @RequestBody Usuario usuario,BindingResult result){
		
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
		}

		Usuario usuarioCreado = usuarioServicio.createUsuario(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") Long id,
			@RequestBody Usuario usuario){
		
		usuario.setIdusuario(id);
		Usuario usuarioEncontrado = usuarioServicio.updateUsuario(usuario);
		
		if(usuarioEncontrado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuarioEncontrado);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") Long id){
		Usuario usuarioDelete = usuarioServicio.deleteUsuario(id);
		
		if(usuarioDelete == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuarioDelete);
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
