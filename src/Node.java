//Mihir Naik
//115348123
//R32
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class Node {
    private String name;
    private HashSet<Edge> edges;
    private boolean visited;
    private LinkedList<String> path;
    private int distance;

    /**
     * Constructor to initialize a Node with a name.
     * 
     * @param name The name of the city represented by this Node.
     */
    public Node(String name) {
        this.name = name;
        this.edges = new HashSet<>();
        this.visited = false;
        this.path = new LinkedList<>();
        this.distance = Integer.MAX_VALUE; // Initially set to infinity for Dijkstra's algorithm
    }

    // Getters and setters for all attributes

    /**
     * Get the name of the city represented by this Node.
     * 
     * @return The name of the city.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the city represented by this Node.
     * 
     * @param name The name of the city.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the set of edges (connections to adjacent cities) from this Node.
     * 
     * @return The set of edges.
     */
    public HashSet<Edge> getEdges() {
        return edges;
    }

    /**
     * Set the set of edges (connections to adjacent cities) from this Node.
     * 
     * @param edges The set of edges.
     */
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Check if the node has been visited.
     * 
     * @return True if the node has been visited, false otherwise.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Set the visited status of the node.
     * 
     * @param visited The visited status.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Get the path to this node.
     * 
     * @return The path to this node.
     */
    public LinkedList<String> getPath() {
        return path;
    }

    /**
     * Set the path to this node.
     * 
     * @param path The path to this node.
     */
    public void setPath(LinkedList<String> path) {
        this.path = path;
    }

    /**
     * Get the distance to this node.
     * 
     * @return The distance to this node.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Set the distance to this node.
     * 
     * @param distance The distance to this node.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    // Other methods can be added as needed
}

