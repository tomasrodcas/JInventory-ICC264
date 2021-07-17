package Window;

import DAO.ProveedorDAO;
import DTO.ProveedorDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
                new ProveedorDAO().addProveedor(new ProveedorDTO(Integer.parseInt(idTextField.getText()), nombreTextField.getText()
                        , Integer.parseInt(rutTextField.getText()), emailTextField.getText(), Integer.parseInt(telefonoTextField.getText())));
                idTextField.setText("");
                nombreTextField.setText("");
                rutTextField.setText("");
                emailTextField.setText("");
                telefonoTextField.setText("");
                tablaProveedores.setModel(new DefaultTableModel(null,nombreColumnas));
                rellenarTabla(new ProveedorDAO().getProveedoresDB());
            }
        });
        tablaProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaProveedores.getModel();
                if(tablaProveedores.getSelectedRow() != -1){
                    new ProveedorDAO().deleteProveedorById(Integer.parseInt((String) modelo.getValueAt(
                            tablaProveedores.getSelectedRow(),0)));
                    modelo.removeRow(tablaProveedores.getSelectedRow());
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
