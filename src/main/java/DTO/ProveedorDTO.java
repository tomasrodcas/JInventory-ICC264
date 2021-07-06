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

    @Override
    public boolean equals(Object o) {
        if( o == this){
            return true;
        }
        if(!(o instanceof ProveedorDTO)){
            return false;
        }
        ProveedorDTO c = (ProveedorDTO) o;

        return this.getNombre().equals(c.getNombre()) && this.getRut() == c.getRut() && this.getEmail().equals(c.getEmail())
               && this.getTelefono() == c.getTelefono();

    }
}
