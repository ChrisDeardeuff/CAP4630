import java.util.ArrayList;

public class AStar {

    ArrayList<Node> arrayOfNodes;

    public AStar(ArrayList<Node> arrayOfNodes) {
        this.arrayOfNodes = arrayOfNodes;
    }

    public ArrayList<Node> runSearch(){

        ArrayList<PathObject> pq = new ArrayList<>();

        Node s = arrayOfNodes.get(0);
        Node e = arrayOfNodes.get(1);

        pq.add(new PathObject(Main.distance(s.x,s.y,e.x,e.y),0,s));
        PathObject minNode = null;

        while(!pq.isEmpty()){

            //find cheapest node
            double mincost = Double.MAX_VALUE;
            for (PathObject po: pq) {

                if(po.cost < mincost){
                    mincost = po.cost;
                    minNode = po;
                }
            }

            if(minNode.n == e){
                return minNode.path;
            }

            minNode.Actions(pq,e);
        }

        return null;
    }

}
class PathObject {

    double cost;
    double g;
    Node n;

    ArrayList<Node> path;

    private PathObject(double cost, double g, Node n, ArrayList<Node> path){
        this.cost = cost+g;
        this.n = n;
        this.g = g;
        this.path = path;
        path.add(this.n);
    }

    public PathObject(double cost, double g, Node n){
        this(cost,g,n,new ArrayList<Node>());
    }

    public void Actions(ArrayList<PathObject> possiblePaths, Node goal){

        possiblePaths.remove(this);
        for (Node n: n.neighbors) {

            possiblePaths.add(new PathObject(Main.distance(n.x,n.y,goal.x,goal.y),this.g+ Main.distance(this.n.x,this.n.y,n.x,n.y),n,(ArrayList<Node>) path.clone()));
        }

    }

}
