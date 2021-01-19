package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class QuizCardPlayer {

      private JTextArea textArea;
      private ArrayList<QuizCard>quizCardArrayList;
      private int currentindex;
      private QuizCard card;
      private boolean isshow;
      private JFrame frame;
      private JPanel mainpanel;
      private JButton nextbutton;

    public static void main(String[] args) {
	 QuizCardPlayer Player=new QuizCardPlayer();
	 Player.go();
    }

    public void go()
    {
        frame=new JFrame("Quiz Card Player");
        mainpanel=new JPanel();
        textArea=new JTextArea(10,20);
        Font bigfont=new Font("sanserif",Font.BOLD,24);
        textArea.setFont(bigfont);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane jScroller=new JScrollPane(textArea);
        jScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextbutton=new JButton("show question");
        nextbutton.setVisible(false);
        nextbutton.addActionListener(new nextbuttonlistener());
        mainpanel.add(jScroller);
        mainpanel.add(nextbutton);
        JMenuBar menuBar=new JMenuBar();
        JMenu filemenu=new JMenu("File");
        JMenuItem loadmenuitem=new JMenuItem("Load Card Set");
        loadmenuitem.addActionListener(new loadmenuListener());
        filemenu.add(loadmenuitem);
        menuBar.add(filemenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(mainpanel);
        frame.setSize(500,600);
        frame.setVisible(true);
    }

    public  class nextbuttonlistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isshow)
            {
                textArea.setText(card.getAnswer());
                nextbutton.setText("Next Card");
                isshow=false;
            }else if(currentindex<quizCardArrayList.size())
            {
                shownextcard();
            }else{
                textArea.setText("That was the last Card");
                nextbutton.setVisible(false);
            }
        }
    }
    public class loadmenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen=new JFileChooser();
            fileopen.showOpenDialog(frame);
            loadfile(fileopen.getSelectedFile());
        }
    }
    private void loadfile(File file)
    {
        quizCardArrayList=new ArrayList<>();
        try{
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line=null;
            while((line=reader.readLine())!=null)
            {
                makecard(line);
            }
            reader.close();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        currentindex=0;
        nextbutton.setVisible(true);
        shownextcard();
    }

    private void shownextcard() {
        card=quizCardArrayList.get(currentindex);
        currentindex++;
        textArea.setText(card.getQuestion());
        nextbutton.setText("show Answer");
        isshow=true;
    }

    private void makecard(String line)
    {
        String[] result=line.split("/");
        QuizCard card=new QuizCard(result[0],result[1]);
        quizCardArrayList.add(card);
        System.out.println("make a card");
    }

}
