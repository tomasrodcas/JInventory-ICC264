package DTO;

import Utils.Hash;

public class UsuarioDTO {
    private final String nombre;
    private final String usuario;
    private final String password;

    public UsuarioDTO(String nombre, String usuario, String password, boolean fromDB){
        this.nombre = nombre;
        this.usuario = usuario;
        if(fromDB){
            this.password = password;
        }else{
            this.password = new Hash().hashPassword(password);
        }
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

    @Override
    public boolean equals(Object o) {
        if( o == this){
            return true;
        }
        if(!(o instanceof UsuarioDTO)){
            return false;
        }

        UsuarioDTO c = (UsuarioDTO) o;

        return this.getNombre().equals(c.getNombre()) && this.getUsuario().equals(c.getUsuario())
                && this.getPassword().equals(c.getPassword());
    }


}
