// --== CS400 File Header Information ==--
// Name: Pranav Vogeti
// Email: vogeti@wisc.edu
// Group and Team: G35
// Group TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in its node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * Constructor that sets the map that the graph uses.
     * @param map the map that the graph uses to map a data object to the node
     *        object it is stored in
     */
    public DijkstraGraph(MapADT<NodeType, Node> map) {
        super(map);
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        if (!this.nodes.containsKey(start) || !this.nodes.containsKey(end)) {
            throw new NoSuchElementException("Graph does not contain these nodes");
        }

        PriorityQueue<SearchNode> pathPQ = new PriorityQueue<>();
        PlaceholderMap<NodeType, Number> visitedNodes = new PlaceholderMap<>();
        SearchNode startNode = new SearchNode(this.nodes.get(start), 0, null);

        pathPQ.add(startNode);

        while (!pathPQ.isEmpty()) {
            //greedily take the next highest searchable node off of the queue
            SearchNode minimumNode = pathPQ.poll();
            //skip if we have seen this node (that means we found the shortest path to this node)
            if (visitedNodes.containsKey(minimumNode.node.data)) {
                continue;
            }

            //we have not seen this node, and so we check if we do match our end
            //if we do, this is the shortest path so return and escape the algorithm
            if (minimumNode.node != null && minimumNode.node.data.equals(end)) {
                return minimumNode;
            }

            //mark this minimumNode as visited
            visitedNodes.put(minimumNode.node.data, minimumNode.cost);

            //start looking at the next visitable neighbor nodes
            List<Edge> exitingEdges = minimumNode.node.edgesLeaving;
            //consider each outgoing edge of this node
            for (Edge edge : exitingEdges) {
                //analyze the new nodes that we can travel to
                Node successorNode = edge.successor;
                double edgeWeight = edge.data.doubleValue();
                double currentCost = minimumNode.cost;
                double successorNodeCost = currentCost + edgeWeight;
                SearchNode neighborNode = new SearchNode(successorNode, successorNodeCost, minimumNode);

                //if we have not yet visited this new node OR we have but a new shorter path was found
                if (!visitedNodes.containsKey(neighborNode.node.data) || successorNodeCost < visitedNodes.get(successorNode.data).doubleValue()) {
                    pathPQ.add(neighborNode);
                }
            }
        }
        throw new NoSuchElementException("No path from " + start.toString() + " to " + end.toString());
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        List<NodeType> shortestPathList = new LinkedList<>();
        SearchNode endNode = computeShortestPath(start, end);

        while (endNode != null) {
            shortestPathList.add(0, endNode.node.data);
            endNode = endNode.predecessor;
        }

        return shortestPathList;
	}

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        return computeShortestPath(start, end).cost;
    }

    /**
     * A JUnit test that tests whether the implemented
     * DijkstraGraph computes the correct shortest path and has the right shortest cost
     * This test makes use of the graph presented on 11/1 in Florian's Lecture and will test
     * along the shortest path from A to F
     */
    @Test
    public void testShortestPath() {
        //Construct the Graph
        DijkstraGraph graph = new DijkstraGraph<String, Integer>(new PlaceholderMap<>());

        //Load the nodes
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        //Link the nodes with their edges and their edge weights
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "D", 1);
        graph.insertEdge("B", "E", 10);
        graph.insertEdge("C", "D", 5);
        graph.insertEdge("D", "F", 0);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("F", "D", 2);
        graph.insertEdge("F", "H", 4);
        graph.insertEdge("G", "H", 4);


        //Test proper implementation for the shortest path from A to F
        String expectedShortestPathList = "[A, B, D, F]";
        String actualShortestPathList = graph.shortestPathData("A", "F").toString();

        double expectedShortestPathCost = 5;
        double actualShortestPathCost = graph.shortestPathCost("A", "F");

        //confirm equality
        Assertions.assertEquals(expectedShortestPathList, actualShortestPathList);
        Assertions.assertEquals(expectedShortestPathCost, actualShortestPathCost);
    }


    @Test
    public void testComputeShortestPath() {
        //Construct the Graph
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());

        //Load the nodes
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");

        //Link the nodes with their edges and their edge weights
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("B", "C", 3);
        graph.insertEdge("A", "C", 10);


        DijkstraGraph<String, Integer>.SearchNode actualSearchNodeReturn = graph.computeShortestPath("A", "C");

        Assertions.assertEquals("C", actualSearchNodeReturn.node.data);
    }


    /**
     * A JUnit test that tests whether the implemented
     * DijkstraGraph computes the correct shortest path and has the right shortest cost
     * This test makes use of the graph presented on 11/1 in Florian's Lecture
     * This uses the same graph as testShortestPath() but will check the shortest path
     * from A to E rather than A to F
     */
    @Test
    public void testDifferentShortestPath() {
        //Construct the Graph
        DijkstraGraph graph = new DijkstraGraph<String, Integer>(new PlaceholderMap<>());

        //Load the nodes
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        //Link the nodes with their edges and their edge weights
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "D", 1);
        graph.insertEdge("B", "E", 10);
        graph.insertEdge("C", "D", 5);
        graph.insertEdge("D", "F", 0);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("F", "D", 2);
        graph.insertEdge("F", "H", 4);
        graph.insertEdge("G", "H", 4);


        //Test proper implementation for the shortest path from A to E
        String expectedShortestPathList = "[A, B, D, E]";
        String actualShortestPathList = graph.shortestPathData("A", "E").toString();

        double expectedShortestPathCost = 8;
        double actualShortestPathCost = graph.shortestPathCost("A", "E");

        //confirm equality
        Assertions.assertEquals(expectedShortestPathList, actualShortestPathList);
        Assertions.assertEquals(expectedShortestPathCost, actualShortestPathCost);
    }



    /**
     * A JUnit test that tests whether the implemented
     * DijkstraGraph computes the correct shortest path and has the right shortest cost
     * This test makes use of the graph presented on 11/1 in Florian's Lecture
     * This uses the same graph as testShortestPath() but will check along a non-existent
     * path from A to G
     */
    @Test
    public void testInvalidPath() {
        //Construct the Graph
        DijkstraGraph graph = new DijkstraGraph<String, Integer>(new PlaceholderMap<>());

        //Load the nodes
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        //Link the nodes with their edges and their edge weights
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "D", 1);
        graph.insertEdge("B", "E", 10);
        graph.insertEdge("C", "D", 5);
        graph.insertEdge("D", "F", 0);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("F", "D", 2);
        graph.insertEdge("F", "H", 4);
        graph.insertEdge("G", "H", 4);


        //confirm that each call throws the right exception
        Assertions.assertThrows(NoSuchElementException.class, () -> graph.computeShortestPath("A", "G"));
        Assertions.assertThrows(NoSuchElementException.class, () -> graph.shortestPathData("A", "G"));
        Assertions.assertThrows(NoSuchElementException.class, () -> graph.shortestPathCost("A", "G"));
    }


    /**
     * A JUnit test that will consider the case where only the edge weights are changed by an additional constant.
     * In this test, a graph that resembles a triangle will be used.
     * Each vertex has one outgoing edge to the next vertex like a cyclical graph. Initially, this graph
     * will have some constant edge weight <b>w</b> for two edges and
     * the other edge has some edge weight <b>e</b> where <b>e > 2w</b>. The shortest path is then path with cost <b>2w</b>.
     * Then, <b>e, w</b> get buffed with some constant <b>c > (e-2w)</b> such that <b>w = w + c</b> and <b>e = e + c</b>.
     * Then, a new shortest path is then evaluated to just be <b>e = e + c</b> for the same path.
     */
    @Test
    public void testChangingPathAfterAddingConstant() {
        //Construct the Graph
        DijkstraGraph graph = new DijkstraGraph<String, Integer>(new PlaceholderMap<>());

        //Load the nodes
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");


        //Link the nodes with their edges and their edge weights
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("B", "C", 1);
        graph.insertEdge("A", "C", 3);


        //Test proper implementation for the shortest path from A to C
        String expectedShortestPathList = "[A, B, C]";
        String actualShortestPathList = graph.shortestPathData("A", "C").toString();

        double expectedShortestPathCost = 2;
        double actualShortestPathCost = graph.shortestPathCost("A", "C");

        //confirm equality
        Assertions.assertEquals(expectedShortestPathList, actualShortestPathList);
        Assertions.assertEquals(expectedShortestPathCost, actualShortestPathCost);


        final int EDGE_WEIGHT_CONSTANT = 10;

        graph.removeEdge("A", "B");
        graph.removeEdge("B", "C");
        graph.removeEdge("A", "C");

        //Buff the edge weights with the constant
        graph.insertEdge("A", "B", 1 + EDGE_WEIGHT_CONSTANT);
        graph.insertEdge("B", "C", 1 + EDGE_WEIGHT_CONSTANT);
        graph.insertEdge("A", "C", 3 + EDGE_WEIGHT_CONSTANT);


        //Test proper implementation for the shortest path from A to C
        String expectedNewPathList = "[A, C]";
        String actualNewPathList = graph.shortestPathData("A", "C").toString();

        double expectedNewPathCost = 13;
        double actualNewPathCost = graph.shortestPathCost("A", "C");

        //confirm equality
        Assertions.assertEquals(expectedNewPathList, actualNewPathList);
        Assertions.assertEquals(expectedNewPathCost, actualNewPathCost);
    }


    /**
     * A JUnit test that tests whether the implemented
     * DijkstraGraph computes the correct shortest path and has the right shortest cost
     * This test makes use of the creates a graph with 10 nodes labeled 1 to 10
     * and establishes 15 edges between them with respective weights
     */
    @Test
    public void testHugeGraphShortestPath() {
        DijkstraGraph graph = new DijkstraGraph<Integer, Integer>(new PlaceholderMap<>());

        //Create the nodes
        graph.insertNode(1);
        graph.insertNode(2);
        graph.insertNode(3);
        graph.insertNode(4);
        graph.insertNode(5);
        graph.insertNode(6);
        graph.insertNode(7);
        graph.insertNode(8);
        graph.insertNode(9);
        graph.insertNode(10);

        // Create edges with their weights
        graph.insertEdge(1, 2, 3);
        graph.insertEdge(1, 3, 2);
        graph.insertEdge(1, 4, 7);
        graph.insertEdge(2, 3, 1);
        graph.insertEdge(2, 5, 5);
        graph.insertEdge(3, 4, 3);
        graph.insertEdge(3, 6, 8);
        graph.insertEdge(4, 5, 6);
        graph.insertEdge(4, 7, 9);
        graph.insertEdge(5, 6, 2);
        graph.insertEdge(5, 8, 4);
        graph.insertEdge(6, 7, 5);
        graph.insertEdge(6, 9, 3);
        graph.insertEdge(7, 8, 1);
        graph.insertEdge(8, 10, 7);

        //Test proper implementation for the shortest path from 1 to 10
        String expectedShortestPathList = "[1, 2, 5, 8, 10]";
        String actualShortestPathList = graph.shortestPathData(1, 10).toString();

        double expectedShortestPathCost = 19;
        double actualShortestPathCost = graph.shortestPathCost(1, 10);

        Assertions.assertEquals(expectedShortestPathList, actualShortestPathList);
        Assertions.assertEquals(expectedShortestPathCost, actualShortestPathCost);
    }

    /**
     * A Junit test to test a graph after inserting multiple edges. Some edges are replaced
     * by their newer edge weights. This should test the strength of Djikstra algorithm as it will be
     * a costly one to run through with this graph.
     */
    @Test
    public void testMultipleEdgesGraph() {
        DijkstraGraph graph = new DijkstraGraph<Character, Integer>(new PlaceholderMap<>());

        //Create the nodes
        graph.insertNode('a');
        graph.insertNode('b');
        graph.insertNode('c');
        graph.insertNode('d');


        // Create edges with their weights
        graph.insertEdge('a', 'b', 4);
        graph.insertEdge('a', 'b', 5);
        graph.insertEdge('a', 'd', 20);

        graph.insertEdge('b', 'c', 6);
        graph.insertEdge('b', 'c', 4);
        graph.insertEdge('b', 'd', 14);

        graph.insertEdge('c', 'b', 7);
        graph.insertEdge('c', 'd', 7);
        graph.insertEdge('c', 'd', 8);

        graph.insertEdge('d', 'c', 10);


        //Because edges are replaced if they were previously existing
        //the graph has the map [a -> b (5)], [a -> d (20)],  [b -> c (4)], [b -> d (14)], [c -> d (8)]
        //So shortest path from a -> d is expected to have cost = 5 + 4 + 8 = 17

        String expectedPath = "[a, b, c, d]";
        double expectedCost = 17;

        String actualPath = graph.shortestPathData('a', 'd').toString();
        double actualCost = graph.shortestPathCost('a', 'd');

        Assertions.assertEquals(expectedPath, actualPath);
        Assertions.assertEquals(expectedCost, actualCost);
    }
}
