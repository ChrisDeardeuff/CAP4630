import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    static int counter = -1;
    public int name;
    public int x;
    public int y;

    double g;
    int h;

    ArrayList<Node> neighbors;
    Polygon parentP;

    public Node(int x,int y,Polygon p){
        this.x = x;
        this.y = y;
        neighbors = new ArrayList<>();
        name = ++counter;
        parentP = p;

    }

    public Line getLine(Node n){

        return new Line(x,n.x,y,n.y);
    }

    @Override
    public String toString(){

        String s = "";

        for(int i = 0; i < neighbors.size(); i++) {
            s = s + neighbors.get(i).toString();
        }

        return "Node Name: " + name + "\n" +
                "Node Location: " + x + ", " + y + "\n" +
                "Node Neighbors: ";
    }
}
