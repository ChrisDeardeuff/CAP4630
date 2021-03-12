import java.util.ArrayList;

public class PTS {

        ArrayList<Node> arrayOfNodes;
        double c;
        public PTS(ArrayList<Node> arrayOfNodes, double c) {
            this.arrayOfNodes = arrayOfNodes;
            this.c = c;
        }

        public ArrayList<Node> runSearch(){

            ArrayList<pathObject> pq = new ArrayList<>();

            Node s = arrayOfNodes.get(0);
            Node e = arrayOfNodes.get(1);

            pq.add(new pathObject(Main.distance(s.x,s.y,e.x,e.y),0,s));
            pathObject minNode = null;

            while(!pq.isEmpty()){

                //find cheapest node
                double mincost = Double.MAX_VALUE;
                for (pathObject po: pq) {

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

        public double cf(double g,double h){
            return g + h;
        }


    }
    class pathObject{

        double cost;
        double g;
        Node n;

        ArrayList<Node> path;

        private pathObject(double cost, double g, Node n, ArrayList<Node> path){
            this.cost = cost+g;
            this.n = n;
            this.g = g;
            this.path = path;
            path.add(this.n);
        }
        public pathObject(double cost, double g, Node n){
            this(cost,g,n,new ArrayList<Node>());
        }
        public void Actions(ArrayList<pathObject> possiblePaths, Node goal){

            possiblePaths.remove(this);
            for (Node n: n.neighbors) {

                possiblePaths.add(new pathObject(Main.distance(n.x,n.y,goal.x,goal.y),this.g+ Main.distance(this.n.x,this.n.y,n.x,n.y),n,(ArrayList<Node>) path.clone()));
            }

        }

    }

}
