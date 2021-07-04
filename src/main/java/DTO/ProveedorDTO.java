package DTO;

public class ProveedorDTO {
    private final String nombre;
    private final int rut;
    private final String email;
    private final int telefono;

    public ProveedorDTO(String nombre, int rut, String email, int telefono){
        this.nombre = nombre;
        this.rut = rut;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre(){
        return this.nombre;
    }
    public int getRut() {
        return this.rut;
    }
    public String getEmail(){
        return this.email;
    }
    public int getTelefono(){
        return this.telefono;
    }

}
