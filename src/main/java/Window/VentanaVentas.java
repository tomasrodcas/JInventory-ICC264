package Window;

import DAO.ItemDAO;
import DAO.VentaDAO;
import DTO.ItemDTO;
import DTO.VentaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class VentanaVentas extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTable tablaVentas;
    private JButton agregarButton;
    private JTextField IDProductotextField;
    private JTextField CantidadtextField;
    private JTextField RUTtextField;
    private JButton eliminarProductoButton;

    public VentanaVentas(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Ventas");
        this.setVisible(true);
        Object[] columnas = {"ID Venta","ID Producto","Nombre Producto","Cantidad","Total","RUT Cliente","Fecha"};
        tablaVentas.setModel(new DefaultTableModel(null,columnas));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
                new VentaDAO(new VentaDTO(Integer.parseInt(IDProductotextField.getText()),Integer.parseInt(CantidadtextField.getText())
                        ,Integer.parseInt(RUTtextField.getText()),new Date())).executeSale(true);
                IDProductotextField.setText("");
                CantidadtextField.setText("");
                RUTtextField.setText("");

                tablaVentas.setModel(new DefaultTableModel(null,columnas));
                rellenarTabla(new VentaDAO().getVentasDB());
            }
        });
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaVentas.getSelectedRow() != -1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
                    new VentaDAO().deleteSaleById(Integer.parseInt((String) modelo.getValueAt(
                            tablaVentas.getSelectedRow(),5)));
                    modelo.removeRow(tablaVentas.getSelectedRow());
                    tablaVentas.setModel(new DefaultTableModel(null,columnas));
                    rellenarTabla(new VentaDAO().getVentasDB());

                }
            }
        });

    }

    public void rellenarTabla(ArrayList<VentaDTO> items){
        for(VentaDTO item:items){
            DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
            String[] dato = item.toArray();
            modelo.addRow(dato);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
