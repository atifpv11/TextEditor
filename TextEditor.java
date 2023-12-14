import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener{
    JFrame frame;
    JMenuBar menuBar;

    JMenu file,edit;

    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;
    TextEditor(){
        //frame for the entire text editor
        frame = new JFrame();

        //initialize menubar
        menuBar = new JMenuBar();

        //text area for the project
        textArea=new JTextArea();

        //initialize the menus file and edit

        file =new JMenu("File");
        edit =new JMenu("Edit");

        //initialize the menu items for the file
        newFile = new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //add ActionListener to all these items
        //so when clicked on this an actionEvent occurs and some action can be preformed
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add the items to the menu file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize the menu items for the edit
        cut = new JMenuItem("Cut");
        paste=new JMenuItem("Paste");
        copy = new JMenuItem("Copy");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //add ActionListener to all these items
        //so when clicked on this an actionEvent occurs and some action can be preformed
        cut.addActionListener(this);
        paste.addActionListener(this);
        copy.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add the items to the menu file
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //set menu to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar to frame
        frame.setJMenuBar(menuBar);

        //create the panel and set its border and layout
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create a scroll pane with vertical and horizontal scrollbar
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);

        //add panel to frame

        frame.add(panel);
        //set dimensions and layout of the frame
        frame.setBounds(250,350,400,400);
        frame.setTitle("TEXT EDITOR");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //perform close editor operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //perform close editor operation
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open the file button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected file

                File file=fileChooser.getSelectedFile();
                //get path of selected file
                String filePath = file.getPath();
                try{
                    //initalize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //initialize buffer rader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";

                    //Read contents of file line by line take it
                    //into the intermediate and paste to output
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();

                }
            }
        }
        if(actionEvent.getSource()==saveFile) {
            //perform close editor operation
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //if we have clicked on open the file button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file

                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                //save the content to the new File
                try {
                    //initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    //write the content of the file to the new file
                    textArea.write((bufferedWriter));
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();

                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}