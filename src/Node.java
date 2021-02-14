import java.util.HashMap;

public class Node {

    static int counter = -1;
    public int name;
    public double x;
    public double y;
    HashMap<Integer, Double> neighbors;

    public Node(int x,int y){
        this.x = x;
        this.y = y;
        neighbors = new HashMap<>();
        name = counter++;
    }
}
