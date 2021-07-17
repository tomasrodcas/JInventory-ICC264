package DTO;

/**
 * Clase que almacena la informacion de un item
 */
public class ItemDTO {
    private final String nombre;
    private final int cantidad;
    private final int precio;
    private final int proveedor;
    private final String marca;
    private final int id;

    /**
     * Constructor de la clase para crear o editar un item
     * @param nombre nombre del producto
     * @param cantidad cantidad almacenada del producto
     * @param precio precio del producto
     * @param proveedor proveedor del producto (Rut entero sin digito verificador)
     * @param marca marca del producto
     */
    public ItemDTO(String nombre, int cantidad, int precio, int proveedor, String marca) {
        this.id = -1;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.proveedor = proveedor;
        this.marca = marca;
    }

    /**
     * Constructor de la clase utilizado para obtener la informacion de la
     * base de datos. Contiene el identificador de la BD
     * @param id identificador del item
     * @param nombre nombre del producto
     * @param cantidad cantidad almacenada del producto
     * @param precio precio del producto
     * @param proveedor proveedor del producto (Rut entero sin digito verificador)
     * @param marca marca del producto
     */
    public ItemDTO(int id, String nombre, int cantidad, int precio, int proveedor, String marca){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.proveedor = proveedor;
        this.marca = marca;
    }


    public String getNombre() {
        return this.nombre;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    public int getProveedor(){
        return this.proveedor;
    }
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
        String proveedor = Integer.toString(this.proveedor);
        return new String[]{id, this.nombre,cantidad, precio, proveedor, this.marca};
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
                && this.getProveedor() == c.getProveedor() && this.getPrecio() == c.getPrecio()
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
