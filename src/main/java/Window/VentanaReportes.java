package Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaReportes extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTable tablaReportes;
    private JButton verGraficoVentasUltimosButton;
    public VentanaReportes(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setTitle("Proveedores");



        verGraficoVentasUltimosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grafica grafica = new Grafica();
            }
        });

    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
