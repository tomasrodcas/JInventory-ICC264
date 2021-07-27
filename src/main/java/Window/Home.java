package Window;

import DAO.ItemDAO;
import DTO.ItemDTO;
import DAO.ReporteDAO;
import DTO.VentaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Home extends JFrame implements ActionListener {
    private JPanel panel1;
    private JButton clientesButton;
    private JButton proveedoresButton;
    private JButton ventasButton;
    private JButton productosButton;
    private JButton reportesButton;
    private JTable tablaProductos1;
    private JButton actualizarButton;
    private JTable tablaVentasHoy;
    private JTable tablaItemsVendidos;
    private JButton totalIngresado;
    private JButton usuariosButton;

    public Home(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Home");
        this.setVisible(true);

        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception e){

        }

        totalIngresado.setText(Integer.toString(new ReporteDAO().getReporte().getTotalHoy()));

        Object[] nombreColumnas = {"Producto Sin Stock", "ID Producto"};
        tablaProductos1.setModel(new DefaultTableModel(null, nombreColumnas));
        rellenarTablaSinStock(new ItemDAO().getItemsSinStock());


        tablaItemsVendidos.setModel(new DefaultTableModel(null, new String[]{"Items Mas Vendidos"}));
        rellanarTablaMasVendidos(new ReporteDAO().getReporte().getItemsMasVendidos());

        tablaVentasHoy.setModel(new DefaultTableModel(null, new String[]{"ID", "Producto", "Total"}));
        rellenarTablaVentasHoy(new ReporteDAO().getReporte().getVentasHoy());

        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProductos ventanaProductos = new VentanaProductos();
            }
        });
        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { VentanaVentas ventanaVentas = new VentanaVentas();}
        });

        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaClientes ventanaClientes = new VentanaClientes();
            }
        });

        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProveedores ventanaProveedores = new VentanaProveedores();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalIngresado.setText(Integer.toString(new ReporteDAO().getReporte().getTotalHoy()));

                Object[] nombreColumnas = {"Producto Sin Stock", "ID Producto"};
                tablaProductos1.setModel(new DefaultTableModel(null, nombreColumnas));
                rellenarTablaSinStock(new ItemDAO().getItemsSinStock());


                tablaItemsVendidos.setModel(new DefaultTableModel(null, new String[]{"Items Mas Vendidos"}));
                rellanarTablaMasVendidos(new ReporteDAO().getReporte().getItemsMasVendidos());

                tablaVentasHoy.setModel(new DefaultTableModel(null, new String[]{"ID", "Producto", "Total"}));
                rellenarTablaVentasHoy(new ReporteDAO().getReporte().getVentasHoy());
            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaUsuarios ventanaUsuarios= new VentanaUsuarios();
            }
        });
        reportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaReportes ventanaReportes = new VentanaReportes();
            }
        });

    }

    public void rellenarTablaSinStock(ArrayList<ItemDTO> items){
        if(items != null){
            for(ItemDTO item : items){

                DefaultTableModel modelo = (DefaultTableModel) tablaProductos1.getModel();

                String[] datos = new String[]{item.getNombre(), Integer.toString(item.getId())};
                modelo.addRow(datos);
            }
        }
    }
    public void rellenarTablaVentasHoy(ArrayList<VentaDTO> ventas){
        if(ventas != null){
            for(VentaDTO venta : ventas){
                DefaultTableModel modelo = (DefaultTableModel) tablaVentasHoy.getModel();
                String[] datos = new String[]{Integer.toString(venta.getId())
                        , venta.getNombreProducto(), Integer.toString(venta.getTotal())};

                modelo.addRow(datos);
            }
        }
    }

    public void rellanarTablaMasVendidos(List<Entry<ItemDTO, Integer>> itemsMasVendidos){
        if(itemsMasVendidos != null){
            for(Entry<ItemDTO, Integer> entry : itemsMasVendidos){

                DefaultTableModel modelo = (DefaultTableModel) tablaItemsVendidos.getModel();
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
