package DTO;

import Utils.DataValidation;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;

/**
 * Clase encargada de almacenar la informacion de una venta
 */
public class VentaDTO {
    private final int id;
    private final int idProducto;
    private int cantidadVendida;
    private final String rutCliente;
    private final Date fecha;
    private final int  total;
    private final String nombreProducto;

    /**
     * Constructor encargado de almacenar la informacion para editar o crear una nueva venta
     * @param idProducto id del producto vendido
     * @param cantidadVendida cantidad vendida
     * @param rutCliente rut del cliente (numero entero y sin digito verificador)
     * @param fecha fecha de la venta
     */
    public VentaDTO(int idProducto, int cantidadVendida, String rutCliente, Date fecha){
        this.idProducto = idProducto;
        this.cantidadVendida = cantidadVendida;
        this.rutCliente =  rutCliente.replace(".","");
        this.fecha = fecha;
        this.total = 0;
        this.id = -1;
        this.nombreProducto = "";
    }

    /**
     * Constructor encargado de almacenar la informacion de una venta proveniente de la base de datos,
     * posee total e identificador
     * @param idProducto id del producto
     * @param cantidadVendida cantidad vendida
     * @param rutCliente rut del cliente (numero entero y sin digito verificador)
     * @param fecha fecha de la venta
     * @param total total de la venta
     * @param id identificador de la venta
     * @param nombreProducto nombre del producto vendido
     */
    public VentaDTO(int idProducto, int cantidadVendida, String rutCliente,
                    Date fecha, int total, int id, String nombreProducto){

        this.idProducto = idProducto;
        this.cantidadVendida = cantidadVendida;
        this.rutCliente =  rutCliente;
        this.fecha = fecha;
        this.total = total;
        this.id = id;
        this.nombreProducto = nombreProducto;
    }
    public int getIdProducto(){
        return this.idProducto;
    }
    public int getCantidadVendida(){
        return this.cantidadVendida;
    }
    public void setCantidadVendida(int cantidad){
        this.cantidadVendida = cantidad;
    }
    public String getRutCliente(){
        return this.rutCliente;
    }
    public int getId(){
        return this.id;
    }
    public Date getFecha(){
        return this.fecha;
    }
    public int getTotal(){
        return this.total;
    }
    public String getNombreProducto(){return this.nombreProducto;}

    /**
     * Convierte todos los atributos a String y los retorna en un array
     * @return String array conteniendo todos los atributos en orden id, id producto, nombre producto, cantidad vendida, total, rut cliente y fecha
     */
    public String[] toArray() {

        String id = Integer.toString(this.id);
        String idProducto = Integer.toString(this.idProducto);
        String cantidadVendida = Integer.toString(this.cantidadVendida);
        String fecha = this.fecha.toString();
        String total = Integer.toString(this.total);

        return new String[]{id, idProducto, nombreProducto , cantidadVendida, total, rutCliente, fecha};
    }
    /**
     * Compara si los atributos entre un Objeto y el VentaDTO son iguales
     * @param o Objeto a comparar
     * @return booleano resultado de si son iguales o no
     */
    @Override
    public boolean equals(Object o) {
        if( o == this){
            return true;
        }
        if(!(o instanceof VentaDTO)){
            return false;
        }

        VentaDTO c = (VentaDTO) o;

        return this.getIdProducto() == c.getIdProducto() && this.getCantidadVendida() == c.getCantidadVendida()
                && this.getRutCliente().toLowerCase().equals(c.getRutCliente().toLowerCase())
                && this.getFecha().getTime() == c.getFecha().getTime() && this.total == c.getTotal();
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
        result = prime * result + ((Integer.toString(this.idProducto) == null) ? 0 : Integer.toString(idProducto).hashCode());
        return result;
    }


}
