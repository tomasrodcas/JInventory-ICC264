package DTO;

import DAO.ClienteDAO;

/**
 * Clase encargada de almacenar la informacion de un Cliente
 */
public class ClienteDTO {

    private final String nombre;
    private final String email;
    private final int telefono;
    private final String rut;
    private final int id;

    /**
     * Constructor de la clase para crear o editar un cliente.
     * @param nombre nombre del cliente
     * @param email email del cliente
     * @param telefono telefono del cliente
     * @param rut rut del cliente (como entero y sin digito verificador)
     */
    public ClienteDTO(String nombre, String email, int telefono, String rut){
        this.id = -1;
        this.nombre = nombre;
        this.email = email;
        this.telefono  = telefono;
        this.rut = rut;

    }

    /**
     * Constructor de la clase utilizado para recibir la informacion de la
     * base de datos, este contiene identificador
     * @param id identificador del cliente
     * @param nombre nombre del cliente
     * @param email email del cliente
     * @param telefono telefono del cliente
     * @param rut rut del cliente
     */
    public ClienteDTO(int id, String nombre, String email, int telefono, String rut){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
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
    public String getRut(){
        return this.rut;
    }
    public int getId(){return this.id;}

    /**
     * Convierte todos los atributos a String y los retorna en un array
     * @return String array conteniendo todos los atributos en orden id, nombre, email, telefono, rut
     */
    public String[] toArray(){
        String id = Integer.toString(this.id);
        String telefono = Integer.toString(this.telefono);

        return new String[]{id, nombre, email, telefono, rut};
    }

    /**
     * Compara si los atributos entre un Objeto y el ClienteDTO son iguales
     * @param o Objeto a comparar
     * @return booleano resultado de si son iguales o no
     */
    @Override
    public boolean equals(Object o){
        if( o == this){
            return true;
        }
        if(!(o instanceof ClienteDTO)){
            return false;
        }
        ClienteDTO c = (ClienteDTO) o;

        return this.getNombre().toLowerCase().equals(c.getNombre().toLowerCase())
                && this.getEmail().toLowerCase().equals(c.getEmail().toLowerCase())
                && this.getRut().toLowerCase().equals(c.getRut().toLowerCase())
                && this.getTelefono() == c.getTelefono();

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
