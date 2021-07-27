package Window;

import DAO.ReporteDAO;
import DTO.ItemDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class VentanaReportes extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTable tablaReportes;
    private JButton verGraficoVentasUltimosButton;
    private JButton ventasHoyButton;
    private JButton totalHoyButton;
    private JButton totalMesButton;
    private JButton total12MesesButton;

    public VentanaReportes(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Reporte");
        this.setVisible(true);


        totalHoyButton.setText(Integer.toString(new ReporteDAO().getReporte().getTotalHoy()));
        total12MesesButton.setText(Integer.toString(new ReporteDAO().getReporte().getTotal12Meses()));
        totalMesButton.setText(Integer.toString(new ReporteDAO().getReporte().getTotalUltimoMes()));

        tablaReportes.setModel(new DefaultTableModel(null, new String[]{"Items Mas Vendidos", "Ventas"}));
        rellanarTablaMasVendidos(new ReporteDAO().getReporte().getItemsMasVendidos());

        verGraficoVentasUltimosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grafica grafica = new Grafica();
            }
        });

    }

    public void rellanarTablaMasVendidos(List<Map.Entry<ItemDTO, Integer>> itemsMasVendidos){
        if(itemsMasVendidos != null){
            for(Map.Entry<ItemDTO, Integer> entry : itemsMasVendidos){

                DefaultTableModel modelo = (DefaultTableModel) tablaReportes.getModel();
                String[] datos = new String[]{entry.getKey().getNombre(),
                        Integer.toString(entry.getValue())};

                modelo.addRow(datos);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
