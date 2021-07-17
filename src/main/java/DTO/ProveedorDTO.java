package DTO;

/**
 * Clase encargada de almacenar la informacion de un proveedor
 */
public class ProveedorDTO {
    private final String nombre;
    private final int rut;
    private final String email;
    private final int telefono;
    private final int id;

    /**
     * Constructor encargado de almacenar la informacion de un proveedor para su creacion o edicion
     * @param nombre nombre del proveedor
     * @param rut rut del proveedor como entero y sin digito verificador
     * @param email email del proveedor
     * @param telefono telefono del proveedor
     */
    public ProveedorDTO(String nombre, int rut, String email, int telefono){
        this.id = -1;
        this.nombre = nombre;
        this.rut = rut;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Constructor encargado de almacenar la informacion de un proveedor proveniente de la base de datos
     * con su correspondiente identificador
     * @param id identificador del proveedor
     * @param nombre nombre del proveedor
     * @param rut rut del proveedor como entero y sin digito verificador
     * @param email email del proveedor
     * @param telefono telefono del proveedor
     */
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

    /**
     * Convierte todos los atributos a String y los retorna en un array
     * @return String array conteniendo todos los atributos en orden id, nombre, rut, email, telefono
     */
    public String[] toArray(){
        String id = Integer.toString(this.id);
        String rut = Integer.toString(this.rut);
        String telefono =Integer.toString(this.telefono);

        return new String[]{id, nombre, rut, email, telefono};
    }
    /**
     * Compara si los atributos entre un Objeto y el ProveedorDTO son iguales
     * @param o Objeto a comparar
     * @return booleano resultado de si son iguales o no
     */
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
    /**
     * Se encarga de obtener un hashCode para la clase en particular.
     * @return el hashcode generado
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
        return result;
    }

}
