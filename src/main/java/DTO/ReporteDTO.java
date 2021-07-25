package DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Clase encargada de almacenar la informacion del reporte
 */
public class ReporteDTO {

    private final int ventasUltimoMes;
    private final ArrayList<Integer> ventas12Meses;
    private final int totalUltimoMes;
    private final int total12Meses;
    private final List<Entry<ItemDTO, Integer>> itemsMasVendidos;
    private final int ventasHoy;
    private final int totalHoy;

    /**
     * Constructor de la clase que recibe toda la informacion del reporte a almacenar
     * @param ventasUltimoMes ventas del ultimo mes
     * @param ventas12Meses ventas de los ultimos 12 meses
     * @param totalUltimoMes el total del ultimo mes ingresado
     * @param total12Meses el total ingresado en los ultimos 12 meses
     * @param itemsMasVendidos los 5 items mas vendidos de los ultimos 12 meses
     * @param ventasHoy las ventas producidas el dia de hoy
     * @param totalHoy las ventas producidas el dia de hoy
     */
    public ReporteDTO(int ventasUltimoMes, ArrayList<Integer> ventas12Meses, int totalUltimoMes, int total12Meses,
                      List<Entry<ItemDTO, Integer>> itemsMasVendidos, int ventasHoy, int totalHoy){
        this.ventasUltimoMes = ventasUltimoMes;
        this.ventas12Meses = ventas12Meses;
        this.totalUltimoMes = totalUltimoMes;
        this.total12Meses = total12Meses;
        this.itemsMasVendidos = itemsMasVendidos;
        this.ventasHoy = ventasHoy;
        this.totalHoy = totalHoy;
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
    public int getVentasHoy(){return this.ventasHoy;}
    public int getTotalHoy(){return this.totalHoy;}
}
