package MySnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Yilan extends JLabel {
    public Kutu mHead=new Kutu();
    public Timer mTimer;
    public Yem mYem=new Yem();
    public Random mRandom;
    public ArrayList<Kutu> Liste= new ArrayList<>();
    @Override
    public void print(Graphics g) {
        super.print(g);


        Graphics2D g2=(Graphics2D)g;
        Rectangle2D rect=new Rectangle2D.Double(0,0,getWidth(),getHeight());
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(10));
        g2.draw(rect);
    }
    Yilan()
    {
        mRandom =new Random(System.currentTimeMillis());
        addKeyListener(new KlavyeKontrol());
        setFocusable(true);
        mTimer=new Timer(200,new TimerKontrol());
        mTimer.start();

        Liste.add(mHead);
        for (int i=1;i<10;i++){
           KuyrukEkle();
        }
        add(mYem);
        add(mHead);
    }
    public void KuyrukEkle()
    {
        Kutu K=Liste.get(Liste.size()-1).KutuOlustur();
        Liste.add(K);
        add(K);
    }
    public void YemEkle()
    {
        int Width= getWidth()-30- mYem.mGenislik;
        int Height=getHeight()-30- mYem.mGenislik;
        int PosX=10+Math.abs(mRandom.nextInt())%Width;
        int PosY=10+Math.abs(mRandom.nextInt())%Height;

        PosX=PosX-PosX%20;
        PosY=PosY-PosY%20;

        mYem.setPosition(PosX,PosY);
    }
    public void HepsiniYurut(){
        for (int i= Liste.size()-1;i>0;i--)
        {
            Kutu Onceki=Liste.get(i-1);
            Kutu Sonraki=Liste.get(i);

            Liste.get(i).Hareket();
            Sonraki.mYon=Onceki.mYon;
        }
        mHead.Hareket();
    }

    public boolean CarpismaVarmi() {
        int Genislik = getWidth();
        int Yukseklik = getHeight();

        if (mHead.getX() <= 10)
            return true;
        if (mHead.getX() + mHead.mGenislik >= Genislik)
            return true;
        if (mHead.getY() <= 10)
            return true;
        if (mHead.getY() + mHead.mGenislik >= Yukseklik)
            return true;
        for (int i = 1; i < Liste.size(); i++) {
            int X = Liste.get(i).getX();
            int Y = Liste.get(i).getY();

            if ((X == mHead.getX()) && (Y == mHead.getY()))
                return true;

        }
        if ((mYem.getX() == mHead.getX()) && (mYem.getY() == mHead.getY())) {
            KuyrukEkle();
            YemEkle();
        }


            return false;
        }
        class KlavyeKontrol implements KeyListener {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (mHead.mYon != YON.SAG)
                        mHead.mYon = YON.SOL;
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (mHead.mYon != YON.SOL)
                        mHead.mYon = YON.SAG;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (mHead.mYon != YON.ASAGI)
                        mHead.mYon = YON.YUKARI;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (mHead.mYon != YON.YUKARI)
                        mHead.mYon = YON.ASAGI;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        }

        class TimerKontrol implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                HepsiniYurut();
                if (CarpismaVarmi()) {
                    mTimer.stop();
                }
            }
        }
    }