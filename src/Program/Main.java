package Program;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.regex.Pattern;

public class Main extends JFrame {
    String INDICATOR = "isaword";
    protected BKTree dictionaryTree = new BKTree();

    private Dictionary dictionary;
    private StringList correctWordList = new StringList();
    private StringList incorrectWordList;
    private JTextArea textArea;
    private ComponentBar compBar;
    private Highlighter.HighlightPainter highlightPainter;
    private SaveAndLoad saveAndLoad;
    private int caretPos;


    public Main(){
        // One time operation
//        dictionary = new Program.Dictionary();
//        dictionary.readFile();
//        dictionary.countWords();
//        dictionary.fillInfo();
//        dictionary.addToTree(dictionaryTree);

        caretPos = 0;

        load();
        startUp();
    }

    private void load() {
        saveAndLoad = new SaveAndLoad();
        dictionary = saveAndLoad.loadDictionaryInfo();
        dictionaryTree = saveAndLoad.loadDictionary();

    }


    public static void main(String[] args) {
        new Main();

    }

    public void startUp() {
        incorrectWordList = new StringList();
        setSize(950, 950);

        highlightPainter = new HighlightPainter(Color.pink);

        textArea = new JTextArea();

        Font font = new Font("Font", Font.PLAIN, 24);
        textArea.setFont(font);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);




        textArea.removeAll();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Dimension dim = new Dimension();
        dim.setSize(this.getSize());
        textArea.setVisible(true);



        compBar = new ComponentBar(textArea);


        add(compBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

//        Comment: One time operation
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                saveAndLoad.saveDictionary(dictionaryTree);
//                saveAndLoad.saveDictionaryInfo(dictionary);
//                System.exit(0);
//            }
//        });



        InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "spaceAction");
        ActionMap actionMap = textArea.getActionMap();
        actionMap.put("spaceAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTextSpace();
            }
        });



        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                caretPos = textArea.getCaretPosition();
                checkTextClick(caretPos);
                popUpMenuWordRecs(e);



            }

            @Override
            public void mousePressed(MouseEvent e) {
                popUpMenuWordRecs(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                popUpMenuWordRecs(e);

                }
            });
    }




    public void popUpMenuWordRecs(MouseEvent e){
        if (SwingUtilities.isRightMouseButton(e)) {
        JPopupMenu popupMenu = new JPopupMenu();

                    textArea.setCaretPosition(textArea.viewToModel(e.getPoint()));
                    int selectedWordPos = textArea.getCaretPosition();
                    try {
                        int start = Utilities.getWordStart(textArea, selectedWordPos);
                        int end = Utilities.getWordEnd(textArea, selectedWordPos);
                        String text = textArea.getText(start, end - start);
                        if(incorrectWordList.contains(text)){
                            StringList unoptimizedSuggestedWords = dictionaryTree.search(text);
                            if(!unoptimizedSuggestedWords.isEmpty()) {
                                StringList optimizedSuggestedWords = dictionary.optimize(unoptimizedSuggestedWords);
                                if (Character.isUpperCase(text.charAt(0))) {
                                    StringList capitalizedStringList = new StringList();
                                    for (String s : optimizedSuggestedWords) {
                                        String capitaliezd = com.sun.xml.internal.ws.util.StringUtils.capitalize(s);
                                        capitalizedStringList.add(capitaliezd);
                                    }
                                    optimizedSuggestedWords.clear();
                                    optimizedSuggestedWords.addAll(capitalizedStringList);
                                }

                                for (String s : optimizedSuggestedWords) {
                                    JMenuItem suggestedString = new JMenuItem(s);
                                    suggestedString.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            textArea.replaceRange(s, start, end);
                                            if (!textArea.getText().toLowerCase().contains(text.toLowerCase())) {
                                                incorrectWordList.remove(text);
                                            }
                                        }
                                    });
                                    popupMenu.add(suggestedString);
                                }
                            }
                            else{
                                popupMenu.add(new JMenuItem("No recommendations"));
                            }
                            popupMenu.show(e.getComponent(), e.getX(), e.getY());
                            popupMenu.setVisible(true);

                        }
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
        }


     public void checkTextSpace(){
        int newWordPos = textArea.getCaretPosition() - 1;
        checkTextBase(newWordPos);
     }

     public void checkTextClick(int caretPosition){
        checkTextBase(caretPosition-1);


     }

     public void checkTextBase(int caretPosition){
         try {
             int start = Utilities.getWordStart(textArea, caretPosition);
             int end = Utilities.getWordEnd(textArea, caretPosition);
             String newWord = textArea.getText(start, end - start).toLowerCase();
             if(!newWord.contains(" ") && !Pattern.matches("\\p{Punct}",newWord)) {

                 if (!correctWordList.contains(newWord)) {
                     if (!incorrectWordList.contains(newWord)) {
                         List<String> returnList = dictionaryTree.search(newWord);
                         if (!returnList.contains(INDICATOR)) {
                             incorrectWordList.add(newWord);
                             searchAndHighlight(incorrectWordList);
                         }
                         else{
                             removeHilight(newWord);
                         }

                     }
                 } else {
                     correctWordList.add(newWord);



                 }
             }

         } catch (BadLocationException e){
             System.out.println("Bad location");
         }
     }


    public void checkText(){

        String textInArea = textArea.getText().toLowerCase();
        String[] arrayOfWords = textInArea.split("\\W+");
        StringList stringList = new StringList();
        for(String s: arrayOfWords){
            if(!stringList.contains(s) && !correctWordList.contains(s)){
                if(!incorrectWordList.contains(s)) {
                    stringList.add(s);
                }
            }
        }
        for(String s: stringList) {
                List<String> returnList = dictionaryTree.search(s);
                if (!returnList.contains(INDICATOR)) {
                    if (!incorrectWordList.contains(s)) {
                        incorrectWordList.add(s.toLowerCase());
                        searchAndHighlight(incorrectWordList);
                    } else {
                        correctWordList.add(s);
                    }


                }

            }
        }



    public void searchAndHighlight(StringList listofIncorrectWords) {
        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (e.getMark() == e.getDot()) {
                    Highlighter textAreaHighLighter = textArea.getHighlighter();
                    textAreaHighLighter.removeAllHighlights();
                    String text = textArea.getText().toLowerCase();

                    for(String s: listofIncorrectWords) {
                        int index = text.indexOf(s.toLowerCase());
                        while (index >= 0) {
                            try {
                                Object o = textAreaHighLighter.addHighlight(index, index + s.length(), highlightPainter);
                                index = text.indexOf(s, index + s.length());
                            } catch (BadLocationException exception) {

                            }
                        }
                    }
                }
            }
        });



    }

    public void removeHilight(String string){
        Highlighter highlighter = textArea.getHighlighter();
        Highlighter.Highlight[] highlights = highlighter.getHighlights();
        for(int i = 0; i < highlights.length; i++){
            int wordLength = highlights[i].getEndOffset() - highlights[i].getStartOffset();
            if(wordLength < string.length()){
                if(highlights[i].getPainter() instanceof HighlightPainter){
                    highlighter.removeHighlight(highlights[i]);
                    String toRemove = string.substring(0, wordLength);
                    incorrectWordList.remove(toRemove);
                    int[] ints;


                }
            }
        }
    }


}
