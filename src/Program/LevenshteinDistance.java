package Program;

public class LevenshteinDistance{


    public LevenshteinDistance(){

    }


    //Calculates Levenshtein distance to convert String 2 to String 1 (and vice versa)
    public int calculateDistance(char[] string1, char[] string2) {

        //Constructs the DP "table"
        int[][] intsArrays = new int[string1.length + 1][string2.length + 1];

        //Fills in the first column of the table (Convert to empty)
        for (int x = 0; x < intsArrays[0].length; x++) {
            intsArrays[0][x] = x;
        }

        //Fills in the first row of the table (Convert to empty)
        for (int y = 0; y < intsArrays.length; y++) {
            intsArrays[y][0] = y;
        }

        //Fills in rest of the table

        for (int y = 1; y <= string1.length; y++) {
            for (int x = 1; x <= string2.length; x++) {
                //If encountered character are duplicate,then the number of steps required to convert would be the number of steps to
                // convert the characters up to the duplicate in String2 to the characters up to the duplicate character
                // in String1. (Which is just the upper-left diagonal value in the table). Fills in
                if (string1[y - 1] == string2[x - 1]) {
                    intsArrays[y][x] = intsArrays[y - 1][x - 1];
                }

                //Otherwise the number of steps required to convert would just be the minimum of either
                // the top, upper-left diagonal, or left value, in the table, along with a 1 being added
                // to the minum value
                else {

                    intsArrays[y][x] = Math.min(
                            Math.min(intsArrays[y - 1][x - 1], intsArrays[y - 1][x]), intsArrays[y][x - 1])
                            + 1;
                }
            }
        }
        return intsArrays[string1.length][string2.length];
    }
}
