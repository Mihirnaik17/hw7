//Mihir Naik
//115348123
//R32
import java.util.Comparator;

public class CityNameComparator implements Comparator<Node> {

    public int compare(Node n1,  Node n2){
        Node nodeA = n1;
        Node nodeB = n2;
        return nodeA.getName().compareTo(nodeB.getName());
    }
    
}
