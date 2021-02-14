/*From the Deitel and Deitel java textbook*/

import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel
{
    public static void main( String args[] )
    {
        // create frame for PolygonsJPanel
        JFrame frame = new JFrame( "Drawing Polygons" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        PolygonsJPanel polygonsJPanel = new PolygonsJPanel();
        frame.add( polygonsJPanel ); // add polygonsJPanel to frame
        frame.setSize( 1000, 700 ); // set frame size
        frame.setVisible( true ); // display frame
    } // end main
}

// draw polygons and polylines
class PolygonsJPanel extends JPanel{


    public void paintComponent( Graphics g )
    {
        super.paintComponent( g ); // call superclass's paintComponent

        // draw filled polygon with Polygon object
        Polygon polygon1 = new Polygon();
        polygon1.addPoint( 165, 235 );
        polygon1.addPoint( 175, 250 );
        polygon1.addPoint( 270, 400 );
        polygon1.addPoint( 200, 420 );
        polygon1.addPoint( 130, 380 );

        g.fillPolygon( polygon1 );

        Polygon polygon2 = new Polygon();
        polygon2.addPoint( 200, 20 );
        polygon2.addPoint( 235, 55 );
        polygon2.addPoint( 270, 20 );
        g.fillPolygon( polygon2 );

        Polygon polygon3 = new Polygon();
        polygon3.addPoint( 150, 100 );
        polygon3.addPoint( 250, 100 );
        polygon3.addPoint( 250, 200 );
        polygon3.addPoint( 150, 200 );
        g.fillPolygon( polygon3 );



        Polygon polygon4 = new Polygon();
        polygon4.addPoint( 300, 135 );
        polygon4.addPoint( 325, 150 );
        polygon4.addPoint( 425, 200 );
        polygon4.addPoint( 500, 220 );
        polygon4.addPoint( 425, 180 );
        polygon4.addPoint( 520, 180 );
        g.fillPolygon( polygon4 );


        Polygon polygon5 = new Polygon();
        polygon5.addPoint( 350, 350 );
        polygon5.addPoint( 375, 393 );
        polygon5.addPoint( 400, 441 );
        polygon5.addPoint( 410, 519 );
        polygon5.addPoint( 450, 519 );
        polygon5.addPoint( 410, 441 );
        polygon5.addPoint( 400, 393 );
        polygon5.addPoint( 375, 350 );
        g.fillPolygon( polygon5 );

/*
        Polygon polygon6 = new Polygon();
        polygon6.addPoint( 165, 135 );
        polygon6.addPoint( 175, 150 );
        polygon6.addPoint( 270, 200 );
        polygon6.addPoint( 200, 220 );
        polygon6.addPoint( 130, 180 );
        g.fillPolygon( polygon6 );
        */

    } // end method paintComponent
} // end class PolygonsJPanel
