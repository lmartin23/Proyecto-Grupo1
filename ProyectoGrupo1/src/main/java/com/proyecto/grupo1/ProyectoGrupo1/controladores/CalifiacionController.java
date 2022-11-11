package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtCalificacion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calificacion")
public class CalifiacionController {
    @Autowired
    CalificacionService calificacionService;

    @RequestMapping(value = "/calificar", method = RequestMethod.POST)
    public ObjResponse calificar(
            @RequestBody DtCalificacion dtC
    ){
        return calificacionService.calificar(dtC);
    }

    @RequestMapping(value = "/calificar", method = RequestMethod.PUT)
    public ObjResponse modificar(
            @RequestBody DtCalificacion dtC
    ){
        return calificacionService.modificar(dtC);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
    public ObjResponse eliminar(
            @RequestBody DtCalificacion dtC
    ){
        return calificacionService.eliminar(dtC);
    }
}
