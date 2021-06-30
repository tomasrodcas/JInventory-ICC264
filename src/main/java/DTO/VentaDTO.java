package DTO;

import java.sql.*;
import java.util.Scanner;

public class VentaDTO {
    private final int idProducto;
    private final int cantidadVendida;
    private final int rutCliente;



    public VentaDTO(int idProducto, int cantidadVendida, int rutCliente){
        this.idProducto = idProducto;
        this.cantidadVendida = cantidadVendida;
        this.rutCliente =  rutCliente;
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

}