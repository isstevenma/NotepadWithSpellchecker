package Program;

import java.util.ArrayList;

public class StringList extends ArrayList<String> {

    public StringList(){
        super();
    }


    @Override
    public boolean contains(Object o){
        String givenString = (String)o;
        for(String string: this ){
            if(givenString.equalsIgnoreCase(string)){
                return true;
            }
        }
        return false;
    }



}
