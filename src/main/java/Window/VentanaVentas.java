package Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaVentas extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTable tablaVentas;
    private JButton agregarButton;
    private JTextField IDProductostextField;
    private JTextField CantidadtextField;
    private JTextField RUTtextField;
    private JTextField FechatextField;
    private JButton eliminarProductoButton;
    private JTextField textField1;

    public VentanaVentas(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Ventas");
        this.setVisible(true);
        Object[] columnas = {"ID Producto","Cantidad","RUT Cliente","Fecha","ID Venta"};

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
