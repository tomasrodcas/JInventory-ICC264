package DTO;

public class ItemDTO {
    private int id;
    private String nombre;
    private int cantidad;
    private int precio;
    private int proveedor;
    private String marca;

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
