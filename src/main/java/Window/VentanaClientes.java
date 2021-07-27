package Window;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import Utils.DataValidation;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaClientes extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField emailTextField;
    private JTextField telefonoTextField;
    private JTable tablaClientes;
    private JButton agregarButton;
    private JButton eliminarClienteButton;
    private JTextField rutTextField;
    private JButton editarButton;

    public VentanaClientes(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Clientes");
        this.setVisible(true);


        Object [] nombreColumnas = {"ID","Nombre","Email","Telefono","RUT"};
        tablaClientes.setModel(new DefaultTableModel(null,nombreColumnas));
        rellenarTabla(new ClienteDAO().getClientesDB());
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();

                String nombre = nombreTextField.getText();
                String email = emailTextField.getText();
                String telefono = telefonoTextField.getText();
                String rut = rutTextField.getText();

                if(new DataValidation().clienteDTOValidation(nombre, email, telefono, rut)){
                    new ClienteDAO().addCliente(new ClienteDTO(nombre, email, Integer.parseInt(telefono), rut));

                    rutTextField.setText("");
                    idTextField.setText("");
                    nombreTextField.setText("");
                    emailTextField.setText("");
                    telefonoTextField.setText("");

                }else{
                    JOptionPane.showMessageDialog(null,"Datos incorrectos");
                }

                tablaClientes.setModel(new DefaultTableModel(null,nombreColumnas));
                rellenarTabla(new ClienteDAO().getClientesDB());

            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();
                String nombre = nombreTextField.getText();
                String email = emailTextField.getText();
                String telefono = telefonoTextField.getText();
                String rut = rutTextField.getText();

                if(new DataValidation().idValidation(id)){
                    if(new DataValidation().clienteDTOValidation(nombre, email, telefono, rut)){
                        boolean editado = new ClienteDAO().editClienteById(new ClienteDTO(nombre, email,
                                Integer.parseInt(telefono), rut), Integer.parseInt(id));
                        if(editado){
                            rutTextField.setText("");
                            idTextField.setText("");
                            nombreTextField.setText("");
                            emailTextField.setText("");
                            telefonoTextField.setText("");
                            tablaClientes.setModel(new DefaultTableModel(null,nombreColumnas));
                            rellenarTabla(new ClienteDAO().getClientesDB());
                            JOptionPane.showMessageDialog(null,"Creado Exitosamente!");

                        }else{
                            JOptionPane.showMessageDialog(null,"Error al editar!");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Datos Incorrectos!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"ID Incorrecto!");
                }

            }
        });

        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tablaClientes.getSelectedRow()!= -1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
                    boolean eliminado = new ClienteDAO().deleteClienteById(
                            Integer.parseInt((String) modelo.getValueAt(
                            tablaClientes.getSelectedRow(),0)));

                    if(eliminado){
                        tablaClientes.setModel(new DefaultTableModel(null,nombreColumnas));
                        rellenarTabla(new ClienteDAO().getClientesDB());
                        JOptionPane.showMessageDialog(null,"Eliminado Exitosamente!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al eliminar!");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"Selecciona un cliente!");
                }

            }
        });


    }
    public void rellenarTabla(ArrayList<ClienteDTO> clientes){
        for(ClienteDTO cliente:clientes){
            DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
            String[] dato = cliente.toArray();
            modelo.addRow(dato);

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
