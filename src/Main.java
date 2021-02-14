/*From the Deitel and Deitel java textbook*/

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Main extends JPanel
{

    static public ArrayList<Node> arrayOfNodes = new ArrayList<>();
    //create polygons
    static PolygonsJPanel polygonsJPanel = new PolygonsJPanel();

    public static void main( String args[] )
    {

        JFrame frame = new JFrame( "Drawing Polygons");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        //arrayOfNodes.add(new Node())
        getValidPaths();


        frame.add( polygonsJPanel );
        //frame.add(new Line());
        frame.setSize( 1000, 500 ); // set frame size
        frame.setResizable(false);
        frame.setVisible( true ); // display frame

    }

    static public void getValidPaths(){

        //get every vertex from polygons
        for (Polygon p: polygonsJPanel.arrayOfPolygons) {
            int x;
            int y;

            for (int i = 0; i < p.npoints; i++) {
                x = p.xpoints[i];
                y = p.ypoints[i];
                arrayOfNodes.add(new Node(x,y));
            }
        }
        //draw lines between every possible node that can be reached
        for(int i = 0; i < arrayOfNodes.size(); i++){

            Node n = arrayOfNodes.get(i);
            int x1 = n.x;
            int y1 = n.y;

            for(int ii = 0; ii < arrayOfNodes.size(); ii++){

                if(ii == i){
                    continue;
                }
                Node n2 = arrayOfNodes.get(ii);
                int x2 = n2.x;
                int y2 = n2.y;

                double slope = getSlope(x1,y1,x2,y2);

                if(!intersects(x1,x2,y1,y2,slope)){
                    n.neighbors.put(n2,distance(x1,y1,x2,y2));
                    polygonsJPanel.arrayOfLines.add(n.getLine(n2));
                }

            }
        }
    }
    static boolean intersects(Polygon p, Line2D l){

        for (int i = 0; i < p.npoints; i++) {
            int next = (i+1)%p.npoints;

            if((l.getX1() == p.xpoints[i] && l.getY1() == p.ypoints[i] ) || (l.getX1() == p.xpoints[next] && l.getY1() == p.ypoints[next])
            ||(l.getX2() == p.xpoints[i] && l.getY2() == p.ypoints[i] ) || (l.getX2() == p.xpoints[next] && l.getY2() == p.ypoints[next])){
                continue;
            }

            Line2D edge = new Line2D.Double(p.xpoints[i],p.ypoints[i],p.xpoints[next],p.ypoints[next]);

            if(edge.intersectsLine(l)){
                return true;
            }
        }

        return false;
    }

    static boolean intersects(int x1, int x2, int y1, int y2, double slope){

        for (Polygon p: polygonsJPanel.arrayOfPolygons) {
            if(intersects(p, new Line2D.Double(x1,y1,x2,y2))){
                return true;
            }
        }
        return false;
    }

    static double distance(int x1, int y1, int x2, int y2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), 0.5);
    }



    static double getSlope(int x1, int y1, int x2, int y2){

        return (double)(y2-y1)/(double)(x2-x1);
    }

    static double getLine(int x1, int y1, int x, double m){

        return y1 - (m * (x-x1));
    }
}