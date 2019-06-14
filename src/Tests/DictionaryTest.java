package Tests;

import Program.Dictionary;
import Program.StringList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DictionaryTest {
    Dictionary dictionary;


    @BeforeEach
    public void runBeforeEach(){
        dictionary = new Dictionary();
        dictionary.readFile();
        dictionary.countWords();

    }

    @Test
    public void testFillInInfo(){

        dictionary.fillInfo();

        assertTrue(dictionary.getTotalIndividualWords() == 32199);
        assertTrue(dictionary.getWordCount() == 1160252);

    }

    @Test
    public void testFrequency(){
        dictionary.fillInfo();
        double reality = dictionary.calculateProb("the");
        double expected = (double) 79809 / (double) 1160252;
        assertTrue(reality == expected);

        }


    @Test
    public void testOptimizeList6Words(){
        dictionary.fillInfo();
        StringList strings = new StringList();
        strings.add("and");
        strings.add("the");
        strings.add("of");
        strings.add("in");
        strings.add("is");
        strings.add("had");
        StringList returnList = dictionary.optimize(strings);

        assertTrue(returnList.get(0) == "the");
        assertTrue(returnList.get(1) == "of");
        assertTrue(returnList.get(2) == "and");
        assertTrue(returnList.get(3) == "in");
        assertTrue(returnList.get(4) == "is");
        assertTrue(returnList.size() == 5);



    }

    @Test

    public void testOptimizeList3Words(){
        dictionary.fillInfo();
        StringList strings = new StringList();
        strings.add("and");
        strings.add("the");
        strings.add("of");
        StringList returnList = dictionary.optimize(strings);
        assertTrue(returnList.get(0) == "the");
        assertTrue(returnList.get(1) == "of");
        assertTrue(returnList.get(2) == "and");
        assertTrue(returnList.size() == 3);

    }
}
