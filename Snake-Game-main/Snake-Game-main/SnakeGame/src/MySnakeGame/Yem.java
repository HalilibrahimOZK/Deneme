package MySnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Yem extends JLabel {
    public int mGenislik=20;

    Yem(){
        setBounds(20,20,mGenislik,mGenislik);

    }
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        Ellipse2D elipse=new Ellipse2D.Double(0,0,mGenislik,mGenislik);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(4));
        g2.draw(elipse);
        g2.setColor(Color.green);
        g2.fill(elipse);
    }
    public void setPosition(int PosX,int PosY){
        setBounds(PosX,PosY,mGenislik,mGenislik);
    }
}