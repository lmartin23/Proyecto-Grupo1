package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadistica")
public class EstadisticaController {

    @Autowired
    EstadisticaService estadisticaService;

    @RequestMapping(value = "/comprasPorDia", method = RequestMethod.GET)
    public ObjResponse compraPorDia(){
        return estadisticaService.comprasPorDia();
    }

    @RequestMapping(value = "/comprasPorEstado", method = RequestMethod.GET)
    public ObjResponse comprasPorEstado(){
        return estadisticaService.comprasPorEstado();
    }
}
