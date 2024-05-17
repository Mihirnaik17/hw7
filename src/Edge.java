//Mihir Naik
//115348123
//R32
public class Edge implements Comparable<Edge> {
    private Node nodeA;
    private Node nodeB;
    private int cost;

    /**
     * Constructor to initialize an Edge between two nodes with a cost.
     * 
     * @param nodeA The first node.
     * @param nodeB The second node.
     * @param cost  The cost of laying down Internet cable between the two cities.
     */
    public Edge(Node nodeA, Node nodeB, int cost) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.cost = cost;
    }

    // Getters and setters for all attributes

    /**
     * Get the first node of the edge.
     * 
     * @return The first node.
     */
    public Node getNodeA() {
        return nodeA;
    }

    /**
     * Set the first node of the edge.
     * 
     * @param nodeA The first node.
     */
    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    /**
     * Get the second node of the edge.
     * 
     * @return The second node.
     */
    public Node getNodeB() {
        return nodeB;
    }

    /**
     * Set the second node of the edge.
     * 
     * @param nodeB The second node.
     */
    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }

    /**
     * Get the cost of the edge.
     * 
     * @return The cost of the edge.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Set the cost of the edge.
     * 
     * @param cost The cost of the edge.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Compares this edge's cost to another edge's cost.
     * 
     * @param otherEdge The other edge to compare.
     * @return -1 if this edge's cost is less than otherEdge's cost,
     *         0 if equal, and 1 if greater than.
     */
    @Override
    public int compareTo(Edge otherEdge) {
        return Integer.compare(this.cost, otherEdge.getCost());
    }

    /**
     * Returns a string representation of the edge.
     * 
     * @return A string representation of the edge.
     */
    @Override
    public String toString() {
        return String.format("%s - %s : %d", nodeA.getName(), nodeB.getName(), cost);
    }
    
    
}

