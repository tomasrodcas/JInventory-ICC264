package Window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Home extends JFrame implements ActionListener {
    private JPanel panel1;
    private JButton clientesButton;
    private JButton proveedoresButton;
    private JButton ventasButton;
    private JButton productosButton;
    private JButton reportesButton;
    private JTable tablaProductos;
    private JButton actualizarButton;

    public Home(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Home");
        this.setVisible(true);
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        }catch (Exception e){

        }

        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProductos ventanaProductos = new VentanaProductos();
            }
        });
        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { VentanaVentas ventanaVentas = new VentanaVentas();}
        });
        tablaProductos.setModel(new DefaultTableModel(null, new String[]{"Productos sin stock"}));
    }

    public void rellenarTabla(ArrayList<Object[]> datos){
        for(Object[]dato:datos){
            DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
            modelo.addRow(dato);

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
