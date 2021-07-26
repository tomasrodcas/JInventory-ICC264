package DTO;

/**
 * Clase que almacena la informacion de un item
 */
public class ItemDTO {
    private final String nombre;
    private final int cantidad;
    private final int precio;
    private final int idProveedor;
    private final String rutProveedor;
    private final String marca;
    private final int id;

    /**
     * Constructor de la clase para crear o editar un item
     * @param nombre nombre del producto
     * @param cantidad cantidad almacenada del producto
     * @param precio precio del producto
     * @param idProveedor identificador del proveedor del producto
     * @param marca marca del producto
     */
    public ItemDTO(String nombre, int cantidad, int precio, int idProveedor, String marca) {
        this.id = -1;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idProveedor = idProveedor;
        this.marca = marca;
        this.rutProveedor = "";
    }

    /**
     * Constructor de la clase utilizado para obtener la informacion de la
     * base de datos. Contiene el identificador de la BD
     * @param id identificador del item
     * @param nombre nombre del producto
     * @param cantidad cantidad almacenada del producto
     * @param precio precio del producto
     * @param idProveedor identificador del proveedor del producto
     * @param rutProveedor Rut del proveedor del producto
     * @param marca marca del producto
     */
    public ItemDTO(int id, String nombre, int cantidad, int precio, int idProveedor,
                   String rutProveedor, String marca){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idProveedor = idProveedor;
        this.rutProveedor = rutProveedor;
        this.marca = marca;
    }


    public String getNombre() {
        return this.nombre;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    public int getIdProveedor(){
        return this.idProveedor;
    }
    public String getRutProveedor(){return this.rutProveedor;}
    public int getPrecio() {
        return this.precio;
    }
    public String getMarca() {
        return this.marca;
    }
    public int getId(){
        return this.id;
    }

    /**
     * Convierte todos los atributos a String y los retorna en un array
     * @return String array conteniendo todos los atributos en orden id, nombre, cantidad, precio, proveedor, marca
     */
    public String[] toArray(){
        String id = Integer.toString(this.id);
        String cantidad = Integer.toString(this.cantidad);
        String precio = Integer.toString(this.precio);
        return new String[]{id, this.nombre,cantidad, precio, rutProveedor, this.marca};
    }

    /**
     * Compara si los atributos entre un Objeto y el ItemDTO son iguales
     * @param o Objeto a comparar
     * @return booleano resultado de si son iguales o no
     */
    @Override
    public boolean equals(Object o) {
        if(o == this){
            return true;
        }
        if(!(o instanceof ItemDTO)){
            return false;
        }
        ItemDTO c = (ItemDTO) o;

        return this.getNombre().toLowerCase().equals(c.getNombre().toLowerCase()) && this.getCantidad() == c.getCantidad()
                && this.getIdProveedor() == c.getIdProveedor() && this.getPrecio() == c.getPrecio()
                && this.getMarca().toLowerCase().equals(c.getMarca().toLowerCase());
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
