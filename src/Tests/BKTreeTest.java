
package Tests;


import Program.BKTree;
import Program.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BKTreeTest {

    BKTree treeBlank;
    BKTree treeWithWord;
    Node testNode;
    BKTree treeWithNode;
    String testWord;


    @BeforeEach
    public void runBeforeEach(){
        treeBlank = new BKTree();
        treeWithWord = new BKTree("Test");
        testNode = new Node("Tests");
        treeWithNode = new BKTree(testNode);
        testWord = "Word";
    }

@Test
    public void testAddEmptyTree(){
        treeBlank.add(testWord);
        assertEquals(treeBlank.getRoot_Node().getValue(), testWord);
}

@Test
    public void testAddNodedTree(){
        treeWithNode.add(testWord);
        treeWithWord.add(testWord);
        treeWithWord.getRoot_Node().getChildren().containsValue(new Node(testWord));
        treeWithNode.getRoot_Node().getChildren().containsValue(new Node(testWord));
}

@Test
    public void testSearchCorrectWord(){
        assertTrue(treeWithWord.search("Test").contains("isaword"));
        assertTrue(treeWithNode.search("Tests").contains("isaword"));
        treeBlank.add(testWord);
        assertTrue(treeBlank.search(testWord).contains("isaword"));

    }

    @Test
    public void testSearchIncorrectWord(){
        treeWithNode.add("testt");
        treeWithWord.add("testt");
        treeBlank.add(testWord);
        treeBlank.add("testt");
        assertTrue(treeWithNode.search("test").contains("testt"));
        assertTrue(treeWithWord.search("testtt").contains("testt"));

        assertTrue(treeBlank.search("test").contains("testt"));
    }


    @Test
    public void testSearchIncorrectWordMultiple(){
        treeWithNode.add("testt");
        treeWithNode.add("xestt");
        treeWithWord.add("testt");
        treeWithWord.add("xestt");
        treeBlank.add(testWord);
        treeBlank.add("testt");
        treeBlank.add("xestt");
        assertTrue(treeWithNode.search("estt").contains("testt"));
        assertTrue(treeWithNode.search("estt").contains("xestt"));
        assertTrue(treeWithWord.search("estt").contains("testt"));
        assertTrue(treeWithWord.search("estt").contains("xestt"));

        assertTrue(treeBlank.search("estt").contains("testt"));
        assertTrue(treeBlank.search("estt").contains("xestt"));
    }



}
