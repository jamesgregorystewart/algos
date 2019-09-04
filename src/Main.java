import java.util.*;

public class Main {

    static class Node {
        int val;
        List<Node> neighbors;

        public Node() {}
    }

    public static void main(String [] args) {
        Node nA = new Node();
        Node nB = new Node();
        Node nC = new Node();
        Node nD = new Node();

        nA.val = 1;
        nB.val = 2;
        nC.val = 3;
        nD.val = 4;

        nA.neighbors.add(nB);
        nA.neighbors.add(nD);

        nB.neighbors.add(nA);
        nB.neighbors.add(nC);

        nC.neighbors.add(nB);
        nC.neighbors.add(nD);

        nD.neighbors.add(nA);
        nD.neighbors.add(nC);

        cloneGraph(nA);
    }

    public static Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> vertexMap = new HashMap<>(); //ref from original to clone
        Queue<Node> q =  new ArrayDeque<>(); //list of neighbors

        q.add(node);
        vertexMap.put(node, new Node());

        //create the ref vertexMap of originals to clones
        while(!q.isEmpty()) {
            Node oNode = q.remove();
            for (Node nei : oNode.neighbors) {
                //copy vertex
                if (vertexMap.putIfAbsent(nei, new Node()) == null) {
                    vertexMap.get(nei).val = nei.val;
                    q.add(nei);
                }
                //copy edge
                vertexMap.get(oNode).neighbors.add(vertexMap.get(nei));
            }
        }
        return vertexMap.get(node);
    }
}
