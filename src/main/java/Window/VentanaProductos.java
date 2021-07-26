package Window;

import DAO.ItemDAO;
import DTO.ItemDTO;
import Utils.DataValidation;

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
    private JButton editarButton;

    public VentanaProductos(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Productos");
        this.setVisible(true);
        Object [] nombreColumnas = {"ID","Nombre","Cantidad","Precio","Rut Proveedor","Marca"};

        tablaproductos.setModel(new DefaultTableModel(null,nombreColumnas));
        rellenarTabla(new ItemDAO().getItemsDB());

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();

                String nombre = nombreTextField.getText();
                String cantidad = cantidadTextField1.getText();
                String precio = precioTextField2.getText();
                String idProveedor = proveedorTextField.getText();
                String marca = marcaTextField.getText();

                if(new DataValidation().itemDTOValidation(nombre, cantidad, precio, idProveedor, marca)){
                    boolean creado = new ItemDAO().addItem(new ItemDTO(nombre,Integer.parseInt(cantidad), Integer.parseInt(precio),
                            Integer.parseInt(idProveedor), marca));
                    if(creado){
                        nombreTextField.setText("");
                        cantidadTextField1.setText("");
                        precioTextField2.setText("");
                        proveedorTextField.setText("");
                        marcaTextField.setText("");
                        IDtextField.setText("");

                        tablaproductos.setModel(new DefaultTableModel(null,nombreColumnas));
                        rellenarTabla(new ItemDAO().getItemsDB());
                        JOptionPane.showMessageDialog(null,"Creado Exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al crear");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Datos Incorrectos");
                }



            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();

                String idProducto = IDtextField.getText();
                String nombre = nombreTextField.getText();
                String cantidad = cantidadTextField1.getText();
                String precio = precioTextField2.getText();
                String idProveedor = proveedorTextField.getText();
                String marca = marcaTextField.getText();

                if(new DataValidation().idValidation(idProducto)){
                    if(new DataValidation().itemDTOValidation(nombre, cantidad, precio, idProveedor, marca)){
                        boolean creado = new ItemDAO().editItemById(Integer.parseInt(idProducto),
                                new ItemDTO(nombre,Integer.parseInt(cantidad), Integer.parseInt(precio),
                                Integer.parseInt(idProveedor), marca));
                        if(creado){
                            nombreTextField.setText("");
                            cantidadTextField1.setText("");
                            precioTextField2.setText("");
                            proveedorTextField.setText("");
                            marcaTextField.setText("");
                            IDtextField.setText("");

                            tablaproductos.setModel(new DefaultTableModel(null,nombreColumnas));
                            rellenarTabla(new ItemDAO().getItemsDB());
                            JOptionPane.showMessageDialog(null,"Creado Exitosamente!");
                        }else{
                            JOptionPane.showMessageDialog(null,"Error al crear!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Datos Incorrectos!");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"ID Incorrecto!");
                }

            }
        });
        tablaproductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaproductos.getSelectedRow() != -1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaproductos.getModel();
                    boolean eliminado = new ItemDAO().deleteItemById(
                            Integer.parseInt((String) modelo.getValueAt(
                            tablaproductos.getSelectedRow(),0)));
                    if(eliminado){
                        tablaproductos.setModel(new DefaultTableModel(null,nombreColumnas));
                        rellenarTabla(new ItemDAO().getItemsDB());
                        JOptionPane.showMessageDialog(null,"Eliminado Exitosamente!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al eliminar!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Selecciona un item!");
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
