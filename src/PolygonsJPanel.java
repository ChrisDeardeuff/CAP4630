import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

// draw polygons
public class PolygonsJPanel extends JPanel {

    public ArrayList<Polygon> arrayOfPolygons = new ArrayList<>();
    public ArrayList<Line>  arrayOfLines = new ArrayList<>();
    public Polygon start, end;

    public PolygonsJPanel(){
        init();
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

    public void init(){
        //START
        start = new Polygon();
        polygonInitiate(start,30,5,50,250,0);

        Polygon poly1 = new Polygon();
        polygonInitiate(poly1,4,100,150,100,45);
        arrayOfPolygons.add(poly1);

        Polygon poly2 = new Polygon();
        polygonInitiate(poly2,5,150,175,300,180);
        arrayOfPolygons.add(poly2);


        Polygon poly3 = new Polygon();
        polygonInitiate(poly3,8,100,400,100,20);
        arrayOfPolygons.add(poly3);


        Polygon poly4 = new Polygon();
        polygonInitiate(poly4,3,150,500,248,45);
        arrayOfPolygons.add(poly4);


        Polygon poly5 = new Polygon();
        polygonInitiate(poly5,4,50,300,168,33);
        arrayOfPolygons.add(poly5);


        Polygon poly6 = new Polygon();
        polygonInitiate(poly6,7,200,800,175,45);
        arrayOfPolygons.add(poly6);

        Polygon poly7 = new Polygon();
        polygonInitiate(poly7,10,100,800,399,0);
        arrayOfPolygons.add(poly7);

        //END
        end = new Polygon();
        polygonInitiate(end,30,5,950,250,0);
    }

    public void paintComponent( Graphics g )
    {
        super.paintComponent(g); // call superclass's paintComponent

        g.setColor(Color.orange);
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
} // end class PolygonsJPanel

