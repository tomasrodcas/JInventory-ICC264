package DTO;

import Utils.DataValidation;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;
public class VentaDTO {
    private final int id;
    private final int idProducto;
    private int cantidadVendida;
    private final int rutCliente;
    private final Date fecha;
    private final int  total;
    private final String nombreProducto;


    public VentaDTO(int idProducto, int cantidadVendida, int rutCliente, Date fecha){
        this.idProducto = idProducto;
        this.cantidadVendida = cantidadVendida;
        this.rutCliente =  rutCliente;
        this.fecha = fecha;
        this.total = 0;
        this.id = -1;
        this.nombreProducto = "";
    }
    public VentaDTO(int idProducto, int cantidadVendida, int rutCliente, Date fecha, int total, int id, String nombreProducto){
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
    public int getRutCliente(){
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

    public String[] toArray() {

        String id = Integer.toString(this.id);
        String idProducto = Integer.toString(this.idProducto);
        String cantidadVendida = Integer.toString(this.cantidadVendida);
        String rutCliente = new DataValidation().getCompleteRut(this.rutCliente);
        String fecha = this.fecha.toString();
        String total = Integer.toString(this.total);

        return new String[]{id, idProducto, nombreProducto , cantidadVendida, total, rutCliente, fecha};
    }

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
                && this.getRutCliente() == c.getRutCliente() && this.getFecha().getTime() == c.getFecha().getTime()
                && this.total == c.getTotal();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((Integer.toString(this.idProducto) == null) ? 0 : Integer.toString(idProducto).hashCode());
        return result;
    }


}
