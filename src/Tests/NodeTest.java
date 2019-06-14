package Tests;

import Program.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {
    Node parentNode;
    Node childNode;
    Node nodeToAdd;


    @BeforeEach
    public void beforeEach(){
        parentNode = new Node("Staryu");
        childNode = new Node(parentNode,"Starmie");
        nodeToAdd = new Node("Star");
    }


    @Test
    public void testAdd(){
        childNode.add(nodeToAdd);
        assertTrue(childNode.hasChildren());
        assertTrue(childNode.getChildren().values().size() == 1);
        assertTrue(childNode.getChildren().get(3).getValue() == "Star");
    }

    @Test
    public void testAddSameLD(){
        childNode.add(nodeToAdd);
        assertTrue(childNode.hasChildren());
        assertTrue(childNode.getChildren().values().size() == 1);
        assertTrue(childNode.getChildren().get(3).getValue() == "Star");
        childNode.add(new Node("Stary"));
        assertTrue(childNode.hasChildren());
        assertTrue(childNode.getChildren().values().size() == 1);
        assertTrue(childNode.getChildren().get(3).getValue() == "Star");
        assertTrue(nodeToAdd.hasChildren());
        assertTrue(nodeToAdd.getChildren().values().size() == 1);
        assertTrue(nodeToAdd.getChildren().get(1).getValue() == "Stary");


    }


}
