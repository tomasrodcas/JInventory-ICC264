package Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaProductos extends JFrame implements ActionListener {


    private JPanel panel1;
    private JTable tablaproductos;
    private JButton agregarButton;
    private JTextField nombreTextField;
    private JTextField marcaTextField;
    private JTextField categoriaTextField;
    private JTextField cantidadTextField;
    private JTextField precioTextField;
    private JTextField rentabilidadTextField;

    public VentanaProductos(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
