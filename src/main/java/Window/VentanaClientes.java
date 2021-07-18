package Window;

import DAO.ClienteDAO;
import DTO.ClienteDTO;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaClientes extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField nombretextField;
    private JTextField emailtextField;
    private JTextField telefonotextField;
    private JTextField ruttextField;
    private JTable tablaClientes;
    private JButton agregarButton;
    private JButton eliminarClienteButton;
    private JTextField idTextField;

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
                new ClienteDAO().addCliente((new ClienteDTO(Integer.parseInt(idTextField.getText()),nombretextField.getText()
                        ,emailtextField.getText(),Integer.parseInt(telefonotextField.getText()),Integer.parseInt(ruttextField.getText()))));
                idTextField.setText("");
                nombretextField.setText("");
                emailtextField.setText("");
                telefonotextField.setText("");
                ruttextField.setText("");
                tablaClientes.setModel(new DefaultTableModel(null,nombreColumnas));
                rellenarTabla(new ClienteDAO().getClientesDB());

            }
        });

        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tablaClientes.getSelectedRow()!= -1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
                    new ClienteDAO().deleteClienteById(Integer.parseInt((String) modelo.getValueAt(
                            tablaClientes.getSelectedRow(),0)));
                    modelo.removeRow(tablaClientes.getSelectedRow());

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
