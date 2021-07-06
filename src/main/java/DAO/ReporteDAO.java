package DAO;

import DTO.ItemDTO;
import DTO.VentaDTO;

import java.util.ArrayList;

public class ReporteDAO{
    private final ArrayList<VentaDTO> ventas;
    private final ArrayList<ItemDTO> items;
    public ReporteDAO(){
        this.ventas = new VentaDAO().getVentasDB();
        this.items = getItemsFromVentas(ventas);
        getSalesPastYear();

    }
    private ArrayList<ItemDTO> getItemsFromVentas(ArrayList<VentaDTO> ventas){
        ArrayList<ItemDTO> items = new ArrayList<>();
        for (VentaDTO venta : ventas){
            items.add(new ItemDAO().getItemById(venta.getIdProducto()));
        }
        return items;
    }
    private void getSalesPastYear(){

    }
    private int getSalesLastMonth(){
        return 1;
    }


}
