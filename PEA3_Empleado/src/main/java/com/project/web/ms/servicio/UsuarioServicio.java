package com.project.web.ms.servicio;

import java.util.List;
import com.project.web.ms.modelo.Roles;
import com.project.web.ms.modelo.Usuario;

public interface UsuarioServicio {

	public List<Usuario> ListAllUsuario();
	public Usuario getUsuario(Long id);
	
	public Usuario createUsuario(Usuario usuario);
	public Usuario updateUsuario(Usuario usuario);
	public Usuario deleteUsuario(Long id);
	
	public List<Usuario> findByRoles(Roles rol);
	
}
