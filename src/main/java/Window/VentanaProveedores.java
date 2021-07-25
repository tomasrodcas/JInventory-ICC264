package Window;

import DAO.ProveedorDAO;
import DTO.ProveedorDTO;
import Utils.DataValidation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaProveedores extends JFrame implements ActionListener {


    private JPanel panel1;
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField rutTextField;
    private JTextField emailTextField;
    private JTextField telefonoTextField;
    private JTable tablaProveedores;
    private JButton agregarProveedorButton;
    private JButton eliminarProveedorButton;

    public VentanaProveedores(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Proveedores");
        this.setVisible(true);
        Object [] nombreColumnas = {"ID","Nombre","RUT","Email","Telefono"};

        tablaProveedores.setModel(new DefaultTableModel(null,nombreColumnas));
        rellenarTabla(new ProveedorDAO().getProveedoresDB());

        agregarProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText();
                String email = emailTextField.getText();
                String rut = rutTextField.getText();
                String telefono = telefonoTextField.getText();

                if(new DataValidation().proveedorDTOValidation(nombre, rut, email, telefono)){
                    boolean creado = new ProveedorDAO().addProveedor(
                            new ProveedorDTO(nombre, rut, email, Integer.parseInt(telefono)));

                    if(creado){
                        idTextField.setText("");
                        nombreTextField.setText("");
                        rutTextField.setText("");
                        emailTextField.setText("");
                        telefonoTextField.setText("");
                        tablaProveedores.setModel(new DefaultTableModel(null,nombreColumnas));
                        rellenarTabla(new ProveedorDAO().getProveedoresDB());

                        JOptionPane.showMessageDialog(null,"Creado Exitosamente!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Error al crear!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Datos incorrectos!");
                }
            }
        });

        agregarProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = idTextField.getText();
                String nombre = nombreTextField.getText();
                String email = emailTextField.getText();
                String rut = rutTextField.getText();
                String telefono = telefonoTextField.getText();

                if (new DataValidation().idValidation(id)) {

                    if(new DataValidation().proveedorDTOValidation(nombre, rut, email, telefono)){
                        boolean creado = new ProveedorDAO().editProveedorById(Integer.parseInt(id),
                                new ProveedorDTO(nombre, rut, email, Integer.parseInt(telefono)));

                        if(creado){
                            idTextField.setText("");
                            nombreTextField.setText("");
                            rutTextField.setText("");
                            emailTextField.setText("");
                            telefonoTextField.setText("");
                            tablaProveedores.setModel(new DefaultTableModel(null,nombreColumnas));
                            rellenarTabla(new ProveedorDAO().getProveedoresDB());

                            JOptionPane.showMessageDialog(null,"Creado Exitosamente!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Error al crear!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Datos incorrectos!");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"ID Invalido!");
                }

            }
        });

        tablaProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaProveedores.getModel();
                if(tablaProveedores.getSelectedRow() != -1){
                    boolean eliminado = new ProveedorDAO().deleteProveedorById(Integer.parseInt((String) modelo.getValueAt(
                            tablaProveedores.getSelectedRow(),0)));
                    if(eliminado){
                        JOptionPane.showMessageDialog(null,"Eliminado Correctamente!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al eliminar!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Selecciona un Proveedor!");
                }
            }
        });

    }

    public void rellenarTabla(ArrayList<ProveedorDTO> proveedores){
        for(ProveedorDTO proveedor: proveedores){
            DefaultTableModel modelo = (DefaultTableModel) tablaProveedores.getModel();
            String[] dato = proveedor.toArray();
            modelo.addRow(dato);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
