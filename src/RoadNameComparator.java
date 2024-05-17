//Mihir Naik
//115348123
//R32
import java.util.Comparator;

public class RoadNameComparator implements Comparator<Edge> {
    
    public int compare(Edge n1,  Edge n2){
        String FirstcityName1 = n1.getNodeA().getName();
        String SeconfCityName1 = n1.getNodeA().getName();
        String FirstcityName2 = n2.getNodeA().getName();
        String SecondcityName2 = n2.getNodeB().getName();

        return FirstcityName1.compareTo(FirstcityName2);

    }
}
