package Window;

import DAO.ItemDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaProductos extends JFrame implements ActionListener {


    private JPanel panel1;
    private JTable tablaproductos;
    private JButton agregarButton;
    private JTextField nombreTextField;
    private JTextField cantidadTextField1;
    private JTextField precioTextField2;
    private JTextField proveedorTextField;
    private JTextField marcaTextField;
    private JButton eliminarProductoButton;
    private JTextField textField1;

    public VentanaProductos(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Productos");
        this.setVisible(true);
        Object [] nombreColumnas = {"Nombre","Cantidad","Precio","Proveedor","Marca","ID"};
        tablaproductos.setModel(new DefaultTableModel(null,nombreColumnas));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();
                modelo.addRow(new String[]{nombreTextField.getText(),cantidadTextField1.getText(),precioTextField2.getText(),
                proveedorTextField.getText(),marcaTextField.getText()});
                nombreTextField.setText("");
                cantidadTextField1.setText("");
                precioTextField2.setText("");
                proveedorTextField.setText("");
                marcaTextField.setText("");

            }
        });
        tablaproductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaproductos.getSelectedRow() != -1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();
                    modelo.removeRow(tablaproductos.getSelectedRow());
                    new ItemDAO().deleteItemById(Integer.parseInt((String) tablaproductos.getModel().getValueAt(
                            tablaproductos.getSelectedRow(),5)));
                }

            }
        });


    }

    public void rellenarTabla(ArrayList<Object[]> datos){
        for(Object[]dato:datos){
            DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();
            modelo.addRow(dato);

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
