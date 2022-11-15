package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.Rol;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Administrador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdministradorDao extends CrudRepository<Administrador, String> {
    Administrador findAdministradorByCorreoIgnoreCase(String correo);
    Administrador findAdministradorByDocumentoIgnoreCase(String documento);
    List<Administrador> findAllByIdContainingAndCorreoContainingAndRolAndNombreContaining(Long id, String correo, Rol rol, String nombre);
}
