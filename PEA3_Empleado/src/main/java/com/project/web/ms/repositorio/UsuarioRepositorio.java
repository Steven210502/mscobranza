package com.project.web.ms.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.web.ms.modelo.Roles;
import com.project.web.ms.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	public List<Usuario> findByRoles(Roles rol);
	
}
