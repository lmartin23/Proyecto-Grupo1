package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente,String> {
    //Usamos esta o JpaRepository
}
