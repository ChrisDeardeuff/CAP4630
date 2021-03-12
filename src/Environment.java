import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Environment extends JPanel {

    public ArrayList<Polygon> arrayOfPolygons;
    public ArrayList<Line>  arrayOfLines;
    public Polygon start, end;
    public Node s,e;

    public Environment(int en){
        arrayOfLines = new ArrayList<>();
        arrayOfPolygons = new ArrayList<>();

        switch (en) {
            case 1 -> {
                initB();
                s = new Node(123, 265, null);
                e = new Node(641, 24, null);
            }
            case 2 -> {
                initA();
                s = new Node(30, 164, null);
                e = new Node(723, 150, null);
            }
        }
    }

    //n is number of sides of regular polygon, l is side length
    //x0 is x-coordinate of center, y0 is y-coordinate of center
    //tilt is clockwise tilt of polygon from positive y axis in degrees
    void polygonInitiate(Polygon polygon, int n, int l, int x0, int y0, double tilt) {

        double theta = 2 * Math.PI / n;
        tilt = tilt * Math.PI / 180;
        double r = 0.5 * l / cos(theta/2); //outer radius of polygon
        for(int i = 0; i < n; i++) {
            int x = (int)(x0 - r*sin(i*theta - tilt));
            int y = (int)(y0 + r*cos(i*theta - tilt));
            polygon.addPoint(x,y);
        }
    }

    public void initB(){
        //START
        start = new Polygon();
        polygonInitiate(start,30,5,123,265,0);

        Polygon poly1 = new Polygon();
        poly1.addPoint(147,225);
        poly1.addPoint(385,225);
        poly1.addPoint(385,301);
        poly1.addPoint(147,301);
        arrayOfPolygons.add(poly1);

        Polygon poly2 = new Polygon();
        polygonInitiate(poly2,5,100,195,109,160);
        arrayOfPolygons.add(poly2);

        Polygon poly3 = new Polygon();
        polygonInitiate(poly3,3,50,294,151,180);
        arrayOfPolygons.add(poly3);

        Polygon poly4 = new Polygon();
        poly4.addPoint(335,22);
        poly4.addPoint(398,14);
        poly4.addPoint(436,56);
        poly4.addPoint(338,115);
        arrayOfPolygons.add(poly4);

        Polygon poly5 = new Polygon();
        poly5.addPoint(450,23);
        poly5.addPoint(550,23);
        poly5.addPoint(550,180);
        poly5.addPoint(450,180);
        arrayOfPolygons.add(poly5);

        Polygon poly6 = new Polygon();
        polygonInitiate(poly6,3,50,430,212,225);
        arrayOfPolygons.add(poly6);

        Polygon poly7 = new Polygon();
        polygonInitiate(poly7,6,100,560,250,0);
        arrayOfPolygons.add(poly7);

        Polygon poly8 = new Polygon();
        poly8.addPoint(565,45);
        poly8.addPoint(600,20);
        poly8.addPoint(630,50);
        poly8.addPoint(613,197);

        arrayOfPolygons.add(poly8);


        //END
        end = new Polygon();
        polygonInitiate(end,30,5,641,24,0);
        this.setBackground(Color.BLACK);
    }

    public void initA(){
        //START
        start = new Polygon();
        polygonInitiate(start,30,5,30,164,0);

        Polygon poly1 = new Polygon();
        polygonInitiate(poly1,4,100,162,91,45);
        arrayOfPolygons.add(poly1);

        Polygon poly2 = new Polygon();
        polygonInitiate(poly2,5,100,175,225,270);
        arrayOfPolygons.add(poly2);


        Polygon poly3 = new Polygon();
        polygonInitiate(poly3,8,120,430,175,20);
        arrayOfPolygons.add(poly3);


        Polygon poly4 = new Polygon();
        polygonInitiate(poly4,3,60,300,265,45);
        arrayOfPolygons.add(poly4);


        Polygon poly5 = new Polygon();
        polygonInitiate(poly5,9,100,324,100,33);
        arrayOfPolygons.add(poly5);


        Polygon poly6 = new Polygon();
        polygonInitiate(poly6,3,75,600,100,323);
        arrayOfPolygons.add(poly6);

        Polygon poly7 = new Polygon();
        polygonInitiate(poly7,10,148,630,220,0);
        arrayOfPolygons.add(poly7);

        //END
        end = new Polygon();
        polygonInitiate(end,30,5,723,150,0);

        this.setBackground(Color.BLACK);
    }

    public void paintComponent( Graphics g )
    {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        for (Polygon p :arrayOfPolygons) {
            g.fillPolygon(p);
        }

        g.fillPolygon(start);
        g.fillPolygon(end);

        g.setColor(Color.GREEN);

        for (Line l: arrayOfLines) {
            g.drawLine(l.x1, l.y1, l.x2, l.y2);
        }

    } // end method paintComponent
} // end class Environment
