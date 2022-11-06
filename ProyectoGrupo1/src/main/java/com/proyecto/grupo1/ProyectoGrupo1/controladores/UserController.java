package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.UserGeneric;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class UserController {

    @GetMapping
    public Map<String, Object> getUserName() {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserGeneric usr = authentication.getClass()
        // usr = (UserGeneric) authentication.getPrincipal(); //Obtengo el userGeneric con los datos del usuario que esta loguado

        Map<String, Object> userMap = new HashMap<>();
        //userMap.put("correo", usr.getCorreo());
        //userMap.put("username", authentication.getName());
        userMap.put("error", false);
        userMap.put("Nombre ", "Peñarol");
        userMap.put("Club ", " deportivo Maldonado");
        return userMap;
    }
}
