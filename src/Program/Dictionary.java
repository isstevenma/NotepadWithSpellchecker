package Program;

import java.io.*;
import java.util.*;

public class Dictionary implements Serializable{
    private StringList wordCollection = new StringList();
    private Map<String, Integer> wordFrequency = new HashMap<>();
    private int totalWords;
    private int totalIndividualWords;


    public void readFile() {
        BufferedReader bufferedReader = null;

        File file = new File("big.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String s : Arrays.asList(words)) {

                    wordCollection.add(s.toLowerCase());
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    public void countWords(){
        for(String s: wordCollection){
            if(!wordFrequency.containsKey(s)){
                wordFrequency.put(s,1);
            }
            else if(wordFrequency.containsKey(s)){
                int prevValue = wordFrequency.get(s);
                prevValue += 1;
                wordFrequency.put(s,prevValue);
                }
        }
    }

    public void addToTree(BKTree bkTree){
        Set<String> stringSet = wordFrequency.keySet();
        for(String s: stringSet){
            bkTree.add(s);
        }

    }

    public void fillInfo(){
        totalWords = wordCollection.size();
        totalIndividualWords = wordFrequency.keySet().size();
    }

    public double calculateProb(String s){
        int usages = wordFrequency.get(s);
        double prob = (double) usages / (double) totalWords;
        return prob;
    }

    public StringList optimize(List<String> unoptimizedList){
        StringList optimizedList = new StringList();

        if(!unoptimizedList.isEmpty()) {
            Map<Double, List<String>> unsortedWords = new HashMap<>();


            for (String s : unoptimizedList) {
                double prob = calculateProb(s);
                if(unsortedWords.get(prob)!=null) {
                    List<String> stringList = unsortedWords.get(prob);
                    stringList.add(s);
                }
                else{
                    List<String> newListofString = new ArrayList<>();
                    newListofString.add(s);
                    unsortedWords.put(prob, newListofString);
                }
            }
            Set<Double> unorderedProbsSet = unsortedWords.keySet();

            List<Double> unorderedProbs = new ArrayList<>();
            unorderedProbs.addAll(unorderedProbsSet);
            Collections.sort(unorderedProbs);
            Collections.reverse(unorderedProbs);

            for (int i = 0; i < unorderedProbs.size(); i++) {
                double d = unorderedProbs.get(i);
                optimizedList.addAll(unsortedWords.get(d));
                if(optimizedList.size() >= 5){
                    break;
                }
            }
        }
        return optimizedList;


    }

    public int getWordCount(){
        return totalWords;
    }

    public int getTotalIndividualWords(){
        return totalIndividualWords;
    }


}
