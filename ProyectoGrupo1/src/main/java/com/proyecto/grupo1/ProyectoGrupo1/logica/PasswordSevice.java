package com.proyecto.grupo1.ProyectoGrupo1.logica;

import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.stereotype.Service;


public interface PasswordSevice {
    public String hashearPassword(String contra);
    public boolean verificarHash(String contraBD, String contraLogin);
}
