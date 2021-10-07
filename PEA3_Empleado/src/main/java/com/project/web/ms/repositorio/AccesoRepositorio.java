package com.project.web.ms.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.web.ms.modelo.Acceso;
import com.project.web.ms.modelo.Roles;
@Repository
public interface AccesoRepositorio extends JpaRepository<Acceso, Long>{

	public List<Acceso> findByRoles(Roles rol);
}
