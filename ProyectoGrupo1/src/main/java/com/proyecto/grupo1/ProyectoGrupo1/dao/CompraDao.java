package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoCompra;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompraDao extends CrudRepository<Compra, Long> {

    Compra findCompraById(Long id);
    List<Compra> findCompraByEstadoAndProductoCarrito_Cliente(EstadoCompra estado, Cliente cliente);

}
