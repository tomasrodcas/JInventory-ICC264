package Window;


import DAO.VentaDAO;
import DTO.VentaDTO;
import Utils.DataValidation;

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
    private JTextField IDVenta;
    private JTextField IDProductotextField;
    private JTextField CantidadTextField;
    private JButton eliminarVentaButton;
    private JTextField RutTextField;
    private JButton editarButton;

    public VentanaVentas(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Ventas");
        this.setVisible(true);
        Object[] columnas = {"ID Venta","ID Producto","Nombre Producto","Cantidad","Total","RUT Cliente","Fecha"};

        tablaVentas.setModel(new DefaultTableModel(null,columnas));
        rellenarTabla(new VentaDAO().getVentasDB());

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();

                String idProducto = IDVenta.getText();
                String cantidad = IDProductotextField.getText();
                String rut = CantidadTextField.getText();

                if(new DataValidation().ventaDTOValidation(idProducto, cantidad, rut)){
                    boolean vendido = new VentaDAO(new VentaDTO(Integer.parseInt(idProducto),
                            Integer.parseInt(cantidad),rut,new Date())).executeSale(true);
                    if(vendido){
                        IDVenta.setText("");
                        IDProductotextField.setText("");
                        CantidadTextField.setText("");

                        tablaVentas.setModel(new DefaultTableModel(null,columnas));
                        rellenarTabla(new VentaDAO().getVentasDB());
                        JOptionPane.showMessageDialog(null,"Venta Exitosa!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al generar venta!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Datos invalidos!");
                }
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
                String idVenta = IDVenta.getText();
                String idProducto = IDProductotextField.getText();
                String cantidad = IDProductotextField.getText();
                String rut = CantidadTextField.getText();

                if(new DataValidation().idValidation(idVenta)){
                    if(new DataValidation().ventaDTOValidation(idProducto, cantidad, rut)){
                        boolean vendido = new VentaDAO().editSaleById(Integer.parseInt(idVenta),
                                new VentaDTO(Integer.parseInt(idProducto),
                                Integer.parseInt(cantidad),rut,new Date()));
                        if(vendido){
                            IDVenta.setText("");
                            IDProductotextField.setText("");
                            CantidadTextField.setText("");

                            tablaVentas.setModel(new DefaultTableModel(null,columnas));
                            rellenarTabla(new VentaDAO().getVentasDB());
                            JOptionPane.showMessageDialog(null,"Editado Exitosamente!");
                        }else{
                            JOptionPane.showMessageDialog(null,"Error al editar la venta!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Datos invalidos!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"ID Invalido!");
                }
            }
        });

        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaVentas.getSelectedRow() != -1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
                    boolean eliminado = new VentaDAO().deleteSaleById(Integer.parseInt((String) modelo.getValueAt(
                            tablaVentas.getSelectedRow(),0)));

                    if(eliminado){
                        tablaVentas.setModel(new DefaultTableModel(null,columnas));
                        rellenarTabla(new VentaDAO().getVentasDB());
                        JOptionPane.showMessageDialog(null,"Eliminado correctamente!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al eliminar!");
                    }


                }
            }
        });

    }

    public void rellenarTabla(ArrayList<VentaDTO> ventas){
        for(VentaDTO venta:ventas){
            DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
            String[] dato = venta.toArray();
            modelo.addRow(dato);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
