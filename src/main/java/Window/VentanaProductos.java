package Window;

import DAO.ItemDAO;
import DTO.ItemDTO;

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
    private JTextField IDtextField;

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
                new ItemDAO().addItem(new ItemDTO(nombreTextField.getText(),Integer.parseInt(cantidadTextField1.getText())
                        ,Integer.parseInt(precioTextField2.getText()),Integer.parseInt(proveedorTextField.getText()
                ),marcaTextField.getText()));
                nombreTextField.setText("");
                cantidadTextField1.setText("");
                precioTextField2.setText("");
                proveedorTextField.setText("");
                marcaTextField.setText("");
                IDtextField.setText("");
                tablaproductos.setModel(new DefaultTableModel(null,nombreColumnas));
                rellenarTabla(new ItemDAO().getItemsDB());


            }
        });
        tablaproductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaproductos.getSelectedRow() != -1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();
                    new ItemDAO().deleteItemById(Integer.parseInt((String) modelo.getValueAt(
                            tablaproductos.getSelectedRow(),5)));
                    modelo.removeRow(tablaproductos.getSelectedRow());

                }

            }
        });


    }

    public void rellenarTabla(ArrayList<ItemDTO> items){
        for(ItemDTO item:items){
            DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();
            String[] dato = item.toArray();
            modelo.addRow(dato);

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
