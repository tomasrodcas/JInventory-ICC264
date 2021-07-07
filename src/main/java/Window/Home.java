package Window;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    private JPanel panel1;
    private JButton clientesButton;
    private JButton proveedoresButton;
    private JButton ventasButton;
    private JButton productosButton;
    private JButton reportesButton;
    private JTable table1;

    public Home(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setVisible(true);
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProductos ventanaProductos = new VentanaProductos();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
