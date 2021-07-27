package Window;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import Utils.DataValidation;

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
    private JTextField passwordTextField;
    private JTextField attemptsTextField;
    private JTextField tipoTextField;
    private JButton agregarButton;
    private JButton eliminarUsuarioButton;
    private JButton editarUsuarioButton;
    private JPasswordField passwordField;

    public VentanaUsuarios(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Usuarios");
        this.setVisible(true);

        Object [] nombreColumnas = {"ID","Nombre","Usuario","Intentos de Login","Tipo"};
        tablaUsuarios.setModel(new DefaultTableModel(null,nombreColumnas));
        rellenarTabla(new UsuarioDAO().getUsersDB());



        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();

                String nombre = nombreTextField.getText();
                String usuario = usuarioTextField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = String.valueOf(passwordChars);

                if(new DataValidation().usuarioDTOValidation(nombre, usuario, password)){

                    boolean creado = new UsuarioDAO().addUser(new UsuarioDTO(nombre,usuario,password));

                    if(creado){

                        idTextField.setText("");
                        nombreTextField.setText("");
                        usuarioTextField.setText("");
                        passwordField.setText("");

                        tablaUsuarios.setModel(new DefaultTableModel(null,nombreColumnas));
                        rellenarTabla(new UsuarioDAO().getUsersDB());

                        JOptionPane.showMessageDialog(null,"Creado exitosamente!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al crear!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Datos invalidos!");
                }


            }
        });

        editarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();

                String id = idTextField.getText();
                String nombre = nombreTextField.getText();
                String usuario = usuarioTextField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = String.valueOf(passwordChars);

                if(new DataValidation().idValidation(id)){
                    if(new DataValidation().usuarioDTOValidation(nombre, usuario, password)){
                        boolean creado = new UsuarioDAO().editUserById(Integer.parseInt(id),
                                new UsuarioDTO(nombre,usuario,password));
                        if(creado){

                            idTextField.setText("");
                            nombreTextField.setText("");
                            usuarioTextField.setText("");
                            passwordField.setText("");

                            tablaUsuarios.setModel(new DefaultTableModel(null,nombreColumnas));
                            rellenarTabla(new UsuarioDAO().getUsersDB());

                            JOptionPane.showMessageDialog(null,"Editado exitosamente!");

                        }else{
                            JOptionPane.showMessageDialog(null,"Error al editar!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Datos invalidos!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"ID Invalido!");
                }
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
                    JOptionPane.showMessageDialog(null,"Seleccione un usuario");
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
