//Mihir Naik
//115348123
//R32
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import big.data.DataSource;


public class RoadCalculator {

    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst;

    public static void main(String[] args){

        Scanner enter = new Scanner(System.in);
		boolean control = true;
        graph = new HashMap<String, Node>();
        System.out.print("Please enter graph URL");
        String graphName = enter.nextLine();
        buildGraph(graphName);
        mst = buildMST(graph);
        //printMST(mst);
        // System.out.println("Minimum Spanning Tree:");
        // for (Edge edge : mst) {
        //     System.out.println(edge);
        // }


        while (control) {
            System.out.print("Enter a starting point for shortest path or Q to quit:");
            String input = enter.nextLine();
            if (input.isEmpty()) {
                continue;
            }
            if (input.equals("q") || input.equals("Q")) {
                control = false;
                System.out.println("Goodbye; PSA, there's a cop on the right in 3 miles!");
                break;
            }
            if(!graph.containsKey(input)){
                System.out.println("Cannot find the given city.");
                return;
            }
            System.out.println("Enter a destination: ");
            String input2 = enter.nextLine();
            if (input2.isEmpty()) {
                continue;
            }
            if(!graph.containsKey(input2)){
                System.out.println("Cannot find the given city.");
                return;
            }
    
            int distance = Djikstra(graph, input, input2);
            if (distance != -1) {
                System.out.println("Distance: "+distance);
                Node node = graph.get(input2);
                System.out.println("Path: ");
                while (!node.getPath().isEmpty()) {
                    System.out.print(node.getPath().poll() + ", ");
                }
            } 
        }
    }
    public static HashMap<String, Node> buildGraph(String location){
        try {
            DataSource ds = DataSource.connectXML(location);
            ds.load();
            System.out.println("Loading Map...");
            String cityNamesStr=ds.fetchString("cities");
            String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
            String roadNamesStr=ds.fetchString("roads");
            String[] roadNames=roadNamesStr.substring(1,roadNamesStr.length()-1).split("\",\"");
            System.out.println("Cities:");
            for (String cityName : cityNames) {
                Node city = new Node(cityName);
                graph.put(cityName, city);
            }
            printCityName();
            System.out.println("Roads:");
            for(String road : roadNames){
                String[] roadSplit = road.split(",");
                String cityName1 = roadSplit[0];
                String cityName2 = roadSplit[1];
                if(cityName1.charAt(0) == '"'){
                    cityName1 =  cityName1.substring(1);
                }
                if(roadSplit[2].endsWith("\"")){
                    roadSplit[2] = roadSplit[2].substring(0,roadSplit[2].length()-1 );
                }
                int distance = Integer.parseInt(roadSplit[2]);
                Node nodeA = graph.get(cityName1);
                Node nodeB = graph.get(cityName2);
                Edge edgeAB = new Edge(nodeA, nodeB, distance);
                nodeA.getEdges().add(edgeAB);
                System.out.println(cityName1+ " to "+ cityName2+" "+distance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
        return graph;
    }

    public static LinkedList<Edge> buildMST(HashMap<String, Node> graph) {
        mst = new LinkedList<Edge>();
        RoadDistanceComparator compareDistance = new RoadDistanceComparator();
        PriorityQueue<Node> mstQueue = new PriorityQueue<>(compareDistance);
        for (Node currNode : graph.values()) {
            currNode.setDistance(Integer.MAX_VALUE);
            currNode.setVisited(false);
        }
        Node startNode = graph.get("StartNodeName");
        if (startNode == null) {
            System.out.println("Start node not found in the graph!");
            return null;
        }
        startNode.setDistance(0);
        mstQueue.add(startNode);
        while (!mstQueue.isEmpty()) {
            Node curVertex = mstQueue.poll();
            curVertex.setVisited(true);
            for (Edge edge : curVertex.getEdges()) {
                Node sideNode = edge.getNodeB();
                if (sideNode.isVisited() || edge.getCost() >= sideNode.getDistance()) {
                    continue;
                }
                sideNode.setDistance(edge.getCost());
                sideNode.getPath().add(sideNode.getName()); 
                mstQueue.add(sideNode);
            }
        }
        return mst;
    }
    


    public static int Djikstra(HashMap<String, Node> graph, String source, String dest) {
        Node curNode;
        for (Node nodes : graph.values()) {
            if (nodes.getName().equals(source)) {
                nodes.setDistance(0);
            } else {
                nodes.setDistance(Integer.MAX_VALUE); 
            }
            nodes.setPath(null);
            nodes.setVisited(false); 
        }
    
        PriorityQueue<Node> queue = new PriorityQueue<>(new RoadDistanceComparator());
        queue.add(graph.get(source));
    
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            if (curNode.isVisited()) continue;
            curNode.setVisited(true);
            for (Edge edges : curNode.getEdges()) {
                Node sideNode = edges.getNodeB();
                int sideNodeDistance = curNode.getDistance() + edges.getCost();
                if (sideNodeDistance < sideNode.getDistance()) {
                    if (queue.contains(sideNode)) {
                        queue.remove(sideNode);
                    }
                    sideNode.setDistance(sideNodeDistance);
                    LinkedList<String> newPath;
                    if (curNode.getPath() == null) {
                        newPath = new LinkedList<>();
                    } else {
                        newPath = new LinkedList<>(curNode.getPath());
                    }
                    newPath.add(curNode.getName());
                    sideNode.setPath(newPath);
                    queue.add(sideNode);
                }
            }
        }
        Node destNode = graph.get(dest);
        if (destNode.isVisited()) {
            return destNode.getDistance();
        } else {
            return -1;
        }
    }
    public static void printCityName(){
        List<Node> nodeList = new ArrayList<>(graph.values());
        Collections.sort(nodeList, new CityNameComparator());
        for (Node node : nodeList) {
            System.out.println(node.getName());
        }
    }
    // public static void printMST(LinkedList<Edge> mst) {
    //     if (mst.isEmpty()) {
    //         System.out.println("Minimum Spanning Tree is empty or not found!");
    //         return;
    //     }
    //     System.out.println("Minimum Spanning Tree:");
    //     for (Edge edge : mst) {
    //         System.out.println(edge);
    //     }
    // }


    
}
