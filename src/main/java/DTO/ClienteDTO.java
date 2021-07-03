package DTO;

public class ClienteDTO {

    private final String nombre;
    private final String email;
    private final int telefono;
    private final int rut;

    public ClienteDTO(String nombre, String email, int telefono, int rut){
        this.nombre = nombre;
        this.email = email;
        this.telefono  = telefono;
        this.rut = rut;

    }
    public String getNombre(){
        return this.nombre;
    }
    public String getEmail(){
        return this.email;
    }
    public int getTelefono(){
        return this.telefono;
    }
    public int getRut(){
        return this.rut;
    }
}
