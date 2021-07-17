package DTO;

public class ProveedorDTO {
    private final String nombre;
    private final int rut;
    private final String email;
    private final int telefono;
    private final int id;

    public ProveedorDTO(String nombre, int rut, String email, int telefono){
        this.id = -1;
        this.nombre = nombre;
        this.rut = rut;
        this.email = email;
        this.telefono = telefono;
    }
    public ProveedorDTO(int id, String nombre, int rut, String email, int telefono){
        this.id = id;
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
    public int getId(){
        return this.id;
    }
    public String[] toArray(){
        String id = Integer.toString(this.id);
        String rut = Integer.toString(this.rut);
        String telefono =Integer.toString(this.telefono);

        return new String[]{id, nombre, rut, email, telefono};
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

        return this.getNombre().toLowerCase().equals(c.getNombre().toLowerCase()) && this.getRut() == c.getRut()
                && this.getEmail().toLowerCase().equals(c.getEmail().toLowerCase()) && this.getTelefono() == c.getTelefono();

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
