import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

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


        System.out.println("Starting A*");
        AStar a = new AStar(arrayOfNodes);
        ArrayList<Node> path = a.runSearch();


        for (int i = 1; i < path.size(); i++) {

            Node previousNode = path.get(i- 1);
            Node currentNode = path.get(i);

            polygonsJPanel.arrayOfLines.add(previousNode.getLine(currentNode));

        }


        frame.add( polygonsJPanel );
        //frame.add(new Line());
        frame.setSize( 1000, 500 ); // set frame size
        frame.setResizable(false);
        frame.setVisible( true ); // display frame

    }

    static public void getValidPaths(){
        Node s = new Node(50,250,null);
        Node e = new Node(950,250,null);

        arrayOfNodes.add(s);
        arrayOfNodes.add(e);

        //get every vertex from polygons
        for (Polygon p: polygonsJPanel.arrayOfPolygons) {
            int x;
            int y;

            ArrayList<Node> nodeSomething = new ArrayList<>();

            for (int i = 0; i < p.npoints; i++) {
                x = p.xpoints[i];
                y = p.ypoints[i];
                arrayOfNodes.add(new Node(x,y,p));
                nodeSomething.add(arrayOfNodes.get(arrayOfNodes.size()-1));
            }
            Node n = nodeSomething.get(0);
            Node n2 = nodeSomething.get(p.npoints-1);

            n.neighbors.add(n2);
            //polygonsJPanel.arrayOfLines.add(n.getLine(n2));
            n2.neighbors.add(n);

            for (int i = 1; i < p.npoints; i++) {
                n = nodeSomething.get(i-1);
                n2 = nodeSomething.get(i);

                n2.neighbors.add(n);
                n.neighbors.add(n2);
                //polygonsJPanel.arrayOfLines.add(n.getLine(n2));
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

                if(n.parentP != null && n2.parentP == n.parentP){
                    continue;
                }

                if(!intersects(x1,x2,y1,y2,slope)){
                    n.neighbors.add(n2);
                    //polygonsJPanel.arrayOfLines.add(n.getLine(n2));
                }

            }
        }
    }
    static boolean intersects(Polygon p, Line2D l){

        for (int i = 0; i < p.npoints; i++) {
            int next = (i+1)%p.npoints;

            if((l.getX1() == p.xpoints[i] && l.getY1() == p.ypoints[i] )
                    || (l.getX1() == p.xpoints[next]
                    && l.getY1() == p.ypoints[next])
                    ||(l.getX2() == p.xpoints[i] && l.getY2() == p.ypoints[i] )
                    || (l.getX2() == p.xpoints[next]
                    && l.getY2() == p.ypoints[next]))
            {

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

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), 0.5);
    }

    static double getSlope(double x1, double y1, double x2, double y2){

        return (y2-y1)/(x2-x1);
    }

    static double getSlope(Line2D l){
        return getSlope(l.getX1(),l.getY1(),l.getX2(),l.getY2());
    }

    static boolean parallelLine(Line2D l1, Line2D l2){

        return getSlope(l1) == getSlope(l2);
    }
}