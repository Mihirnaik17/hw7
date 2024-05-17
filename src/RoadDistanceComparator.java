//Mihir Naik
//115348123
//R32
import java.util.Comparator;

public class RoadDistanceComparator implements Comparator<Node> {
    public int compare(Node node1, Node node2) {
        return Integer.compare(node1.getDistance(), node2.getDistance());
    }
}
