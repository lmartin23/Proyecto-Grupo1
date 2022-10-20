package com.proyecto.grupo1.ProyectoGrupo1.logica;

public interface PasswordSevice {
    public String hashearPassword(String contra);
    public boolean verificarHash(String contraBD, String contraLogin);
}
