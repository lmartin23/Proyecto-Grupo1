package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.CompraDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoCompra;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EstadisticaServiceImpl implements EstadisticaService{

    @Autowired
    CompraDao compraDao;

    @Autowired
    ClienteDao clienteDao;
    @Override
    public ObjResponse comprasPorDia() {
        List<Compra> compras = compraDao.findAll();
        Map<String, Integer> estadisticas = new HashMap<String, Integer>();;

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

        for(Compra c : compras){
            String fecha = dt.format(c.getFecha());

            if(estadisticas.get(fecha) == null){
                estadisticas.put(fecha, 1 );
            } else {
                Integer i = estadisticas.get(fecha)+1;
                estadisticas.put(fecha, i);
            }
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(), estadisticas);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse comprasPorEstado() {
        List<Compra> compras = compraDao.findAll();
        Map<EstadoCompra, Integer> estadisticas = new HashMap<EstadoCompra, Integer>();;

        for(Compra c : compras){
            if(estadisticas.get(c.getEstado()) == null){
                estadisticas.put(c.getEstado(), 1 );
            } else {
                Integer i = estadisticas.get(c.getEstado())+1;
                estadisticas.put(c.getEstado(), i);
            }
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(), estadisticas);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
}
