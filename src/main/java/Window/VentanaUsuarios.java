package Window;

import DAO.UsuarioDAO;
import DTO.ClienteDTO;
import DTO.UsuarioDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaUsuarios extends JFrame implements ActionListener {


    private JPanel panel1;
    private JTable tablaUsuarios;
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField usuarioTextField;
    private JTextField contraseñaTextField;
    private JTextField attemptsTextField;
    private JTextField tipoTextField;
    private JButton agregarButton;
    private JButton eliminarUsuarioButton;
    private JButton editarUsuarioButton;

    public VentanaUsuarios(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Clientes");
        this.setVisible(true);
        Object [] nombreColumnas = {"ID","Nombre","Usuario","Intentos de Login","Tipo"};
        tablaUsuarios.setModel(new DefaultTableModel(null,nombreColumnas));
        rellenarTabla(new UsuarioDAO().getUsersDB());

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
                int id = Integer.parseInt(idTextField.getText());
                String nombre = nombreTextField.getText();
                String usuario = usuarioTextField.getText();
                String contraseña = contraseñaTextField.getText();
                int loginAttempts = Integer.parseInt(attemptsTextField.getText());
                int tipo = Integer.parseInt(tipoTextField.getText());
                new UsuarioDAO().addUser(new UsuarioDTO(id,nombre,usuario,contraseña,loginAttempts,tipo));
                idTextField.setText("");
                nombreTextField.setText("");
                usuarioTextField.setText("");
                contraseñaTextField.setText("");
                attemptsTextField.setText("");
                tipoTextField.setText("");
                tablaUsuarios.setModel(new DefaultTableModel(null,nombreColumnas));
                rellenarTabla(new UsuarioDAO().getUsersDB());

            }
        });

        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaUsuarios.getSelectedRow() !=-1){
                    DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
                    boolean eliminado = new UsuarioDAO().deleteUserById(Integer.parseInt((String) modelo.getValueAt(
                            tablaUsuarios.getSelectedRow(),0)));
                    if (eliminado){
                        tablaUsuarios.setModel(new DefaultTableModel(null,nombreColumnas));
                        rellenarTabla(new UsuarioDAO().getUsersDB());
                        JOptionPane.showMessageDialog(null,"Eliminado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al eliminar");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Seleccione un cliente");
                }
            }
        });


    }

    public void rellenarTabla(ArrayList<UsuarioDTO>usuarios){
        for(UsuarioDTO usuario: usuarios){
            DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
            String[] dato = usuario.toArray();
            modelo.addRow(dato);

        }
    }














    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
