import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class AStar {

    ArrayList<Node> arrayOfNodes;
    ArrayList<pathObject> path = new ArrayList<>();
    ArrayList<Node> pq = new ArrayList<>();

    public AStar(ArrayList<Node> arrayOfNodes) {
        this.arrayOfNodes = arrayOfNodes;
    }

    public ArrayList<pathObject> runSearch(){
        Node s = arrayOfNodes.get(0);
        Node e = arrayOfNodes.get(1);

        double h = 0;
        double g = 0;
        Node cn = s;

        pq.add(s);
        Node minNode = null;
        while(!pq.isEmpty()){

            System.out.println(cn.name);
            System.out.println(Main.distance(50,250,cn.x,cn.y));
            System.out.println(g);
            System.out.println();
            if(cn == e){
                return path;
            }

            //find cheapest node

            double mincost = 9999999;


            for (Node n: pq) {
                h = Main.distance(n.x,n.y,950,250);
                double cost = cf(g + Main.distance(n.x,n.y,cn.x,cn.y), h);

                if(cost < mincost){
                    mincost = cost;
                    minNode = n;
                }
            }

            pq.remove(minNode);
            pathObject po = new pathObject(mincost,minNode);
            path.add(po);

            cn = minNode;
            g = g + po.cost;

            ArrayList<Node> expansions = Actions(minNode);
            for (Node n:expansions) {
                pq.add(n);
            }

        }

        return null;
    }

    public double cf(double g,double h){
        return g + h;
    }

    public ArrayList<Node> Actions(Node n){

        return n.neighbors;
    }
}
class pathObject{

    double cost;
    Node n;

    public pathObject(double cost, Node n){
        this.cost =cost;
        this.n = n;
    }

}
