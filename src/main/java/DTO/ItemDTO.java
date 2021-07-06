package DTO;

public class ItemDTO {
    private final String nombre;
    private final int cantidad;
    private final int precio;
    private final int proveedor;
    private final String marca;

    public ItemDTO(String nombre, int cantidad, int precio, int proveedor, String marca) {
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

    @Override
    public boolean equals(Object o) {
        if(o == this){
            return true;
        }
        if(!(o instanceof ItemDTO)){
            return false;
        }
        ItemDTO c = (ItemDTO) o;

        return this.getNombre().equals(c.getNombre()) && this.getCantidad() == c.getCantidad() && this.getProveedor() == c.getProveedor()
               && this.getPrecio() == c.getPrecio() && this.getMarca().equals(c.getMarca());
    }
}
