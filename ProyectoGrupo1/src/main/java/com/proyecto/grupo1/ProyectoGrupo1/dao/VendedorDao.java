package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Direccion;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Queue;


public interface VendedorDao extends CrudRepository<Vendedor, Long> {

    Vendedor findVendedorById(Long id);
    Vendedor findVendedorByCliente(Cliente c);
    List<Vendedor> findAllByHabilitado(boolean habilitado);
    List<Vendedor> findAll();

}
