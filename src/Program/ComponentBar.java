package Program;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ComponentBar extends JPanel {
    private JButton save;
    private JButton load;

    public ComponentBar(JTextArea jTextArea){
        save = new JButton("Save");
        load = new JButton("Load");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSaveButton(jTextArea);
            }
        });

        load.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       setLoadButton(jTextArea);
                                       }});

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(save);
        add(load);

    }

    public void setSaveButton(JTextArea jTextArea) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("C:/Users/Steven/Documents"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        fc.setDialogTitle("Save file");

        fc.setFileFilter(filter);
        int response = fc.showSaveDialog(null);;



        if (response == JFileChooser.APPROVE_OPTION) {
           File savingFile = fc.getSelectedFile();


            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(savingFile.getPath()+".txt"))) {
                jTextArea.write(bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
                }

        }
    }


    public void setLoadButton(JTextArea jTextArea) {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        String filePath;
        fc.setCurrentDirectory(new File("C:/Users/Steven/Documents"));
        fc.setDialogTitle("Open file");
        fc.setFileFilter(filter);
        fc.showOpenDialog(null);
        File f = fc.getSelectedFile();
        if (f == null) {
            filePath = "";
        } else {
            filePath = f.getAbsolutePath();
            try {
                FileReader reader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(reader);
                jTextArea.read(bufferedReader, "textarea");
                bufferedReader.close();
                jTextArea.requestFocus();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
