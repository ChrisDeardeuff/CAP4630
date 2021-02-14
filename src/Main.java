/*From the Deitel and Deitel java textbook*/

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Main extends JPanel
{
    static public ArrayList<Polygon> arrayOfPolygons;
    static public ArrayList<Node> arrayOfNodes = new ArrayList<>();

    public static void main( String args[] )
    {

        JFrame frame = new JFrame( "Drawing Polygons" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        //create polygons
        PolygonsJPanel polygonsJPanel = new PolygonsJPanel();
        arrayOfPolygons = polygonsJPanel.arrayOfPolygons;

        //arrayOfNodes.add(new Node())
        getValidPaths();

        frame.add( polygonsJPanel );
        frame.setSize( 1000, 500 ); // set frame size
        frame.setVisible( true ); // display frame

    }

    static public void getValidPaths(){

        for (Polygon p: arrayOfPolygons) {
            int x;
            int y;

            for (int i = 0; i < p.npoints; i++) {
                x = p.xpoints[i];
                y = p.ypoints[i];
                arrayOfNodes.add(new Node(x,y));
            }
        }
    }
}

// draw polygons and polylines
class PolygonsJPanel extends JPanel{

    public ArrayList<Polygon> arrayOfPolygons = new ArrayList<>();

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

    public void paintComponent( Graphics g )
    {
        super.paintComponent(g); // call superclass's paintComponent

        //START
        Polygon start = new Polygon();
        polygonInitiate(start,30,5,50,250,0);
        g.fillPolygon(start);

        Polygon poly1 = new Polygon();
        polygonInitiate(poly1,4,100,150,100,45);
        g.fillPolygon(poly1);
        arrayOfPolygons.add(poly1);

        Polygon poly2 = new Polygon();
        polygonInitiate(poly2,5,150,175,300,180);
        g.fillPolygon(poly2);
        arrayOfPolygons.add(poly2);

        Polygon poly3 = new Polygon();
        polygonInitiate(poly3,8,100,400,100,20);
        g.fillPolygon(poly3);
        arrayOfPolygons.add(poly3);

        Polygon poly4 = new Polygon();
        polygonInitiate(poly4,3,150,500,248,45);
        g.fillPolygon(poly4);
        arrayOfPolygons.add(poly4);

        Polygon poly5 = new Polygon();
        polygonInitiate(poly5,4,50,300,168,33);
        g.fillPolygon(poly5);
        arrayOfPolygons.add(poly5);

        Polygon poly6 = new Polygon();
        polygonInitiate(poly6,7,200,800,175,45);
        g.fillPolygon(poly6);
        arrayOfPolygons.add(poly6);

        Polygon poly7 = new Polygon();
        polygonInitiate(poly7,10,100,800,399,0);
        g.fillPolygon(poly7);
        arrayOfPolygons.add(poly7);

        //END
        Polygon end = new Polygon();
        polygonInitiate(end,30,5,950,250,0);
        g.fillPolygon(end);


    } // end method paintComponent
} // end class PolygonsJPanel
