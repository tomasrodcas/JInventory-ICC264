package DAO;

import DTO.ReporteDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReporteDAOTest {

    @Test
    void getReporte() {
        int totalHoy = 0;
        int totalMes = 1645850;
        int total12Meses = 1645850;
        int ventasHoy = 0;
        int ventasMes = 5;
        ArrayList<Integer> ventas12Meses = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            if(i == 11){
                ventas12Meses.add(5);
                break;
            }
            ventas12Meses.add(0);
        }

        ReporteDTO reporte = new ReporteDAO().getReporte();

        int totalHoyReporte = reporte.getTotalHoy();
        int totalMesReporte = reporte.getTotalUltimoMes();
        int total12MesesReporte = reporte.getTotal12Meses();
        int ventasHoyReporte = reporte.getVentasHoy().size();
        int ventasMesReporte = reporte.getVentasUltimoMes();
        ArrayList<Integer> ventas12MesesReporte = reporte.getVentas12Meses();

        assertEquals(totalHoy, totalHoyReporte);
        assertEquals(totalMes, totalMesReporte);
        assertEquals(total12Meses, total12MesesReporte);
        assertEquals(ventasHoy, ventasHoyReporte);
        assertEquals(ventasMes, ventasMesReporte);

        for(int i = 0; i < ventas12Meses.size(); i++){
            assertEquals(ventas12Meses.get(i), ventas12MesesReporte.get(i));
        }

    }

}