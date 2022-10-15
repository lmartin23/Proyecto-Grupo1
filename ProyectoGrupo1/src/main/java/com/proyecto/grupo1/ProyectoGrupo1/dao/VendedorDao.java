package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.data.repository.CrudRepository;

public interface VendedorDao extends CrudRepository<Vendedor, Long> {

    Vendedor findVendedorById(Long id);

}
