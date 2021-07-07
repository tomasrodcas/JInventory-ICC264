package Window;

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

    public VentanaProductos(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setVisible(true);
        Object [] nombreColumnas = {"Nombre","Cantidad","Precio","Proveedor","Marca"};
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
