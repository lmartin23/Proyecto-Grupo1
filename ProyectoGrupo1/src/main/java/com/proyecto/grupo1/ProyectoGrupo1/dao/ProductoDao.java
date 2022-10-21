package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.entidades.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoDao extends CrudRepository<Producto, Long> {
    Producto findProductoById(Long id);
    List<Producto> getAllBy();
    List<Producto> getAllByActivo(Boolean status);

}
