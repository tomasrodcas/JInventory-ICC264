package Window;
import DAO.ReporteDAO;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.*;
import javax.swing.*;
import java.util.ArrayList;


public class Grafica extends JFrame {

    public Grafica() {
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        ReporteDAO reporte = new ReporteDAO();
        ArrayList<Integer> ventasAnuales = reporte.getReporte().getVentas12Meses();
        rellenarDataset(ventasAnuales,data);
        JFreeChart grafica = ChartFactory.createBarChart3D("Reporte anual", "Meses", "Ventas", data,
                PlotOrientation.VERTICAL, true, true, false);
        ChartPanel contenedor = new ChartPanel(grafica);
        JFrame ventana = new JFrame("Reporte Anual");
        ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventana.add(contenedor);
        ventana.setSize(500, 600);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

    }


    public void rellenarDataset(ArrayList<Integer> ventasAnuales, DefaultCategoryDataset data) {
        int i = 1;
        for (Integer venta : ventasAnuales) {
            data = new DefaultCategoryDataset();
            data.addValue(venta, "Ventas del mes " + i, "Mes " + i);
            i++;

        }

    }
}


