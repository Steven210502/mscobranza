package com.project.web.ms.servicio;


import java.util.List;
import org.springframework.stereotype.Service;
import com.project.web.ms.modelo.Roles;
import com.project.web.ms.modelo.Usuario;
import com.project.web.ms.repositorio.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImp implements UsuarioServicio{
	
	public final UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public List<Usuario> ListAllUsuario() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario getUsuario(Long id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}

	@Override
	public Usuario createUsuario(Usuario usuario) {
		
		usuario.setEstado(1);
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) {
		Usuario usuarioUpdate = getUsuario(usuario.getIdusuario());
		
		if(usuarioUpdate == null) {
			return null;
		}
		usuarioUpdate.setRoles (usuario.getRoles());
		usuarioUpdate.setNombre(usuario.getNombre());
		usuarioUpdate.setTipodocumento(usuario.getTipodocumento());
		usuarioUpdate.setNumdocumento(usuario.getNumdocumento());
		usuarioUpdate.setDireccion(usuario.getDireccion());
		usuarioUpdate.setTelefono(usuario.getTelefono());
		usuarioUpdate.setEmail(usuario.getEmail());
		usuarioUpdate.setClave(usuario.getClave());
		usuarioUpdate.setEstado(usuario.getEstado());
		
		return usuarioRepositorio.save(usuarioUpdate);
	}

	@Override
	public Usuario deleteUsuario(Long id) {
		Usuario usuarioDelete = getUsuario(id);
		
		if(usuarioDelete == null) {
			return null;
		}
		
		usuarioDelete.setNombre("ELIMINADO"); 
		usuarioDelete.setTipodocumento("ELIMINADO");
		usuarioDelete.setNumdocumento("ELIMINADO");
		usuarioDelete.setDireccion("ELIMINADO");
		usuarioDelete.setTelefono("ELIMINADO");
		usuarioDelete.setEmail("ELIMINADO");
		usuarioDelete.setClave("ELIMINADO");
		usuarioDelete.setEstado(0);
		
		return usuarioRepositorio.save(usuarioDelete);
	}

	@Override
	public List<Usuario> findByRoles(Roles rol) {
		return usuarioRepositorio.findByRoles(rol);
	}

}
