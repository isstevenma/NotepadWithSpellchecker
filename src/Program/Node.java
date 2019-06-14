package Program;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Node implements Serializable {
    private Node parent;
    private String value;
    private Map<Integer,Node> children = new HashMap<>();

    public Node(String s){
        this.value = s;

    }

    public Node(Node n, String s){
        this.parent = n;
        this.value = s;
    }

    public void add(Node n) {
        String rootValue = this.getValue();
        String addValue = n.getValue();
        LevenshteinDistance lVDistance = new LevenshteinDistance();
        int editDistance = lVDistance.calculateDistance(rootValue.toCharArray(), addValue.toCharArray());
        if (children.containsKey(editDistance)) {
            Node childNode = children.get(editDistance);
            getViableNode(n,childNode,editDistance);
        } else {
            children.put(editDistance, n);
        }
    }


    public void getViableNode(Node addingNode,Node childNode, int editDistance){
        LevenshteinDistance lVDistance = new LevenshteinDistance();
        int childEditDistance = lVDistance.calculateDistance(addingNode.getValue().toCharArray(), childNode.getValue().toCharArray());
        if(childNode.getChildren().containsKey(childEditDistance)){
            Node nextChildNode = childNode.getChildren().get(childEditDistance);
            getViableNode(addingNode, nextChildNode, childEditDistance);
        }
        else{
            Map<Integer, Node> childrenOfChildren = childNode.getChildren();
            childrenOfChildren.put(childEditDistance, addingNode);
        }

    }

    public boolean hasChildren(){
        if(children.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    public Map<Integer, Node> getChildren(){
        return children;
    }


    public String getValue(){
        return value;
    }

}
