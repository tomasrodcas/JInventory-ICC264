package DTO;

import Utils.Hash;

public class UsuarioDTO {
    private final String nombre;
    private final String usuario;
    private final String password;

    public UsuarioDTO(String nombre, String usuario, String password){
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = new Hash().hashPassword(password);
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getUsuario(){
        return this.usuario;
    }
    public String getPassword(){
        return this.password;
    }
}
