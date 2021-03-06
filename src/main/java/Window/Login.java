package Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Auth.LoginDAO;
import Auth.LoginDTO;

public class Login extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;

    public Login(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Login");
        this.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField1.getText();
                char[] passwordChars = passwordField1.getPassword();
                String password = String.valueOf(passwordChars);
                if(new LoginDAO().login(new LoginDTO(usuario, password))){
                    Home home = new Home();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Datos incorrectos");
                }


            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
