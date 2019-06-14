package Tests;

import Program.LevenshteinDistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LevenshteinDistanceTest {

    LevenshteinDistance lDistance;


    @BeforeEach
    public void runBeforeEach(){
        lDistance = new LevenshteinDistance();

    }


    @Test
    public void testCalculateDistance(){
        int result = lDistance.calculateDistance("Star".toCharArray(), "Staryu".toCharArray());
        assertTrue(result == 2 );
    }
}
