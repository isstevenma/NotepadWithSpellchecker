package Program;

import java.io.Serializable;
import java.util.*;

public class BKTree implements Serializable{

    private Integer N = 1;
    private Node root_Node;
    private String INDICATOR = "isaword";
    private Map<Integer, Node> trackerOfLastEditDistanceNode = new HashMap<>();

    public BKTree() {
    }

    public BKTree(String s) {
        Node n = new Node(s);
        this.root_Node = n;
    }

    public BKTree(Node n) {
        this.root_Node = n;
    }

    public void add(String s) {
        if (root_Node != null) {
            Node n = new Node(s);
            root_Node.add(n);
        }
        else{
            Node firstNode = new Node(s);
            root_Node = firstNode;
        }
    }

    public StringList search(String s) {
        StringList results = new StringList();

        searchRecursive(s,root_Node, results);
        return results;
    }

    private void searchRecursive(String s, Node node, List<String> results) {
        Set<Integer> edges = node.getChildren().keySet();
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        int currentLvDist = levenshteinDistance.calculateDistance(node.getValue().toCharArray(), s.toCharArray());
        int minDist = currentLvDist - N;
        int maxDist = currentLvDist + N;
        if (currentLvDist <= N) {
            results.add(node.getValue());
        }
        if(currentLvDist == 0) {
            results.add(INDICATOR);

        }
        else {
            for (int i : edges) {
                searchRecursive(s, node.getChildren().get(i), results);
            }
        }
    }



    public Node getRoot_Node(){
        return root_Node;
    }

}
