package DTO;

import java.sql.*;
import java.util.Scanner;

public class VentaDTO {
    private final int idProducto;
    private final int cantidadVendida;
    private final int rutCliente;
    private final Date fecha;



    public VentaDTO(int idProducto, int cantidadVendida, int rutCliente, Date fecha){
        this.idProducto = idProducto;
        this.cantidadVendida = cantidadVendida;
        this.rutCliente =  rutCliente;
        this.fecha = fecha;
    }



    public int getIdProducto(){
        return this.idProducto;
    }
    public int getCantidadVendida(){
        return this.cantidadVendida;
    }
    public int getRutCliente(){
        return this.rutCliente;
    }
    public Date getFecha(){
        return this.fecha;
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
                && this.getRutCliente() == c.getRutCliente();
    }


}
