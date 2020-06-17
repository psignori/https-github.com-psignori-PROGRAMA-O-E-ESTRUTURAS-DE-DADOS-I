/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author patri
 */
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Plot extends JFrame {
    public Plot(ArrayList<Double> cliente, ArrayList<Double> cliente1, ArrayList<Double> fornecedor, ArrayList<Double> fornecedor1) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        PlotComponent1 pcomp = new PlotComponent1(1100, 600);
        add(pcomp);
        for (int g = 0; g<cliente.size(); g++){
            pcomp.addPoint(cliente.get(g)*1100, cliente1.get(g)*600);
        }
        for (int g = 0; g<fornecedor.size(); g++){
            pcomp.addPoint1(fornecedor.get(g)*1100, fornecedor1.get(g)*600 );
        }
        //
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(()-> new Plot());
    }
}

class PlotComponent1 extends JComponent {
    Color a = null;
    private ArrayList<Point2D> points = new ArrayList<Point2D>();
    
    public PlotComponent1(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }
    
    public void addPoint(double x, double y) {
        points.add(new Point2D.Double(x, y));
        a = Color.BLUE;
    }
    public void addPoint1(double x, double y) {
        points.add(new Point2D.Double(x, y));
        a = Color.RED;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(a);
        for (Point2D p : points) {
            Shape point = new Ellipse2D.Double(p.getX(), p.getY(), 2, 2);
            g2d.draw(point);
        }
    }
}
