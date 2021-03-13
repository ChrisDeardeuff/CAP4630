import java.util.ArrayList;

public class PTS {

        ArrayList<Node> arrayOfNodes;

        static double c;

        public PTS(ArrayList<Node> arrayOfNodes, double c) {

            this.arrayOfNodes = arrayOfNodes;
            PTS.c = c;

        }

        public ArrayList<Node> runSearch() {

            ArrayList<PTSPathObject> open = new ArrayList<>();

            Node s = arrayOfNodes.get(0);
            Node e = arrayOfNodes.get(1);

            open.add(new PTSPathObject(Main.distance(s.x,s.y,e.x,e.y),0,s));

            while(open.size() > 0){

                double maxCost = Double.MIN_VALUE;
                PTSPathObject pts = null;

                for (PTSPathObject n:open) {

                    if(n.ptsCost > maxCost && n.ptsCost >= 0){
                        pts = n;
                        maxCost = pts.ptsCost;
                    }
                }

                if(pts == null){
                    return null;
                }

                if(pts.n == e){
                    return pts.path;
                }

                pts.Actions(open,e);

            }
            return null;
        }

    }
class PTSPathObject {
    double ptsCost;
    double cost;
    double g;
    Node n;

    ArrayList<Node> path;

    private PTSPathObject(double cost, double g, Node n, ArrayList<Node> path){
        this.ptsCost = (PTS.c + g) / cost;
        this.cost = cost+g;
        this.n = n;
        this.g = g;
        this.path = path;
        path.add(this.n);
    }

    public PTSPathObject(double cost, double g, Node n){
        this(cost,g,n,new ArrayList<Node>());
    }

    public void Actions(ArrayList<PTSPathObject> possiblePaths, Node goal){

        possiblePaths.remove(this);

        for (Node n: n.neighbors) {

            boolean expanded = false;

            for (PTSPathObject p: possiblePaths) {
                if(p.n.equals(n)){
                    expanded = true;
                    break;
                }
            }

            if(expanded){
                continue;
            }

            if(this.g + Main.distance(this.n.x,this.n.y,n.x,n.y) + this.g + Main.distance(this.n.x,this.n.y,n.x,n.y) > PTS.c ){
                continue;
            }
            possiblePaths.add(new PTSPathObject(Main.distance(n.x,n.y,goal.x,goal.y),this.g+ Main.distance(this.n.x,this.n.y,n.x,n.y),n,(ArrayList<Node>) path.clone()));

        }

    }

}
