package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.entidades.Direccion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DireccionDao extends CrudRepository<Direccion,Long> {
    List<Direccion> getAllByCliente_Id(Long id);

    List<Direccion> getAllByVendedor_Id(Long id);

    Direccion getById(Long id);

}
