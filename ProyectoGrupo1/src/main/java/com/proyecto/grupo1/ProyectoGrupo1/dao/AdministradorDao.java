package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.entidades.Administrador;
import org.springframework.data.repository.CrudRepository;

public interface AdministradorDao extends CrudRepository<Administrador, String> {
    Administrador findClienteByCorreoIgnoreCase(String correo);
    Administrador findAdministradorByDocumentoIgnoreCase(String doc);
}
