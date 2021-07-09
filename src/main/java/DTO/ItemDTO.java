package DTO;

public class ItemDTO {
    private final String nombre;
    private final int cantidad;
    private final int precio;
    private final int proveedor;
    private final String marca;
    private final int id;

    public ItemDTO(String nombre, int cantidad, int precio, int proveedor, String marca) {
        this.id = -1;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.proveedor = proveedor;
        this.marca = marca;
    }
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


    public String[] toArray(){
        String id = Integer.toString(this.id);
        String cantidad = Integer.toString(this.cantidad);
        String precio = Integer.toString(this.precio);
        String proveedor = Integer.toString(this.proveedor);
        return new String[]{id, this.nombre,cantidad, precio, proveedor, this.marca};
    }

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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
        return result;
    }

}
