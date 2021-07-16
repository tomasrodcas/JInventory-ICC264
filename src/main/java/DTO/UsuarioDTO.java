package DTO;

import Utils.Hash;

public class UsuarioDTO {
    private final String nombre;
    private final String usuario;
    private final String password;
    private final int id;
    private final int loginAttempts;
    private final int tipoUsuario;

    public UsuarioDTO(String nombre, String usuario, String password){
        this.id = -1;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = new Hash().hashPassword(password);
        this.loginAttempts = -1;
        this.tipoUsuario = -1;

    }
    public UsuarioDTO(int id, String nombre, String usuario, String password, int loginAttempts, int tipoUsuario){
       this.id = id;
       this.nombre = nombre;
       this.usuario = usuario;
       this.password = password;
       this.loginAttempts = loginAttempts;
       this.tipoUsuario = tipoUsuario;
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
    public int getLogin_attempts(){return this.loginAttempts;}
    public int getTipo_usuario(){return this.tipoUsuario;}

    public String[] toArray(){
        String id = Integer.toString(this.id);
        String login_attempts = Integer.toString(this.loginAttempts);
        String tipo_usuario =  Integer.toString(this.tipoUsuario);
        return new String[]{id, this.nombre, this.usuario, login_attempts, tipo_usuario};
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

        return this.nombre.toLowerCase().equals(c.getNombre().toLowerCase())
                && this.usuario.toLowerCase().equals(c.getUsuario().toLowerCase())
                && this.password.toLowerCase().equals(c.getPassword().toLowerCase())
                && this.tipoUsuario == c.getTipo_usuario() && this.loginAttempts
                == c.getLogin_attempts();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
        return result;
    }


}
