package Program;

import java.io.*;

public class SaveAndLoad {
    BKTree bkTree;
    Dictionary dictionary;

    public void saveDictionary(BKTree bkTree) {


        try (FileOutputStream fs = new FileOutputStream("dictionary.bin")) {
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeObject(bkTree);

            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public BKTree loadDictionary(){
        try(FileInputStream fi = new FileInputStream("dictionary.bin")){
            ObjectInputStream ois = new ObjectInputStream(fi);

            bkTree = (BKTree) ois.readObject();



        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return bkTree;
    }

    public void saveDictionaryInfo(Dictionary dictionary) {
        try (FileOutputStream fs = new FileOutputStream("dictionaryInfo.bin")) {
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeObject(dictionary);

            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dictionary loadDictionaryInfo() {
        try (FileInputStream fi = new FileInputStream("dictionaryInfo.bin")) {
            ObjectInputStream ois = new ObjectInputStream(fi);
            dictionary = (Dictionary) ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

}
