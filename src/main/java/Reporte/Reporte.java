package Reporte;

import DAO.ItemDAO;
import DAO.VentaDAO;
import DTO.ItemDTO;
import DTO.VentaDTO;

import java.util.ArrayList;

public class Reporte {

    private final ArrayList<VentaDTO> ventas;
    private final ArrayList<ItemDTO> items;

    public Reporte(){
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
    private ArrayList<Integer> getSalesPastYear(){
        ArrayList<Integer> salesLastYear = new ArrayList<>(12);

        return salesLastYear;
    }
    private int getSalesLastMonth(){
        return 1;
    }


}
