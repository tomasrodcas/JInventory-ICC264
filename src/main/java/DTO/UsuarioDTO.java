package DTO;

import Utils.Hash;

public class UsuarioDTO {
    private final String nombre;
    private final String usuario;
    private final String password;
    private final int id;

    public UsuarioDTO(String nombre, String usuario, String password){
        this.id = -1;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = new Hash().hashPassword(password);

    }
    public UsuarioDTO(int id, String nombre, String usuario, String password){
       this.id = id;
       this.nombre = nombre;
       this.usuario = usuario;
       this.password = password;
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
    public int getId(){
        return this.id;
    }

    public String[] toArray(){
        String id = Integer.toString(this.id);
        return new String[]{id, this.nombre, this.usuario};
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

        return this.getNombre().toLowerCase().equals(c.getNombre().toLowerCase())
                && this.getUsuario().toLowerCase().equals(c.getUsuario().toLowerCase())
                && this.getPassword().toLowerCase().equals(c.getPassword().toLowerCase());
    }


}
