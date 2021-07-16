package DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class ReporteDTO {

    private final int ventasUltimoMes;
    private final ArrayList<Integer> ventas12Meses;
    private final int totalUltimoMes;
    private final int total12Meses;
    private final List<Entry<ItemDTO, Integer>> itemsMasVendidos;

    public ReporteDTO(int ventasUltimoMes, ArrayList<Integer> ventas12Meses, int totalUltimoMes, int total12Meses,
                      List<Entry<ItemDTO, Integer>> itemsMasVendidos){
        this.ventasUltimoMes = ventasUltimoMes;
        this.ventas12Meses = ventas12Meses;
        this.totalUltimoMes = totalUltimoMes;
        this.total12Meses = total12Meses;
        this.itemsMasVendidos = itemsMasVendidos;
    }

    public int getVentasUltimoMes(){
        return this.ventasUltimoMes;
    }
    public ArrayList<Integer> getVentas12Meses(){
        return this.ventas12Meses;
    }
    public int getTotalUltimoMes(){
        return this.totalUltimoMes;
    }
    public int getTotal12Meses(){
        return this.total12Meses;
    }
    public List<Entry<ItemDTO, Integer>> getItemsMasVendidos(){
        return this.itemsMasVendidos;
    }
}
