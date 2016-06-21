/**
 * ConnectFour.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the connect four game and includes functions such as 
 * building the GUI template, checking different conditions, and making moves with the mouse.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnectFour extends JFrame implements ActionListener {

   public Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
   public Label lblSpacer;
   public MenuItem newMI, exitMI, redMI, yellowMI;
   public int[][] array;
   public boolean end=false;
   public boolean startGame;
   public static final int blank = 0;
   public static final int red = 1;
   public static final int yellow = 2;
   public static final int maxrow = 6;    
   public static final int maxcol = 7;     

   public static final String SPACE = "                  "; 
   int activeColour = red;
  
   //builds content panel, creates JButtons and other objects
   public ConnectFour() {
      setTitle("ConnectFour");
      MenuBar mbar = new MenuBar();
      Menu fileMenu = new Menu("File");
      newMI = new MenuItem("New");
      newMI.addActionListener(this);
      fileMenu.add(newMI);
      exitMI = new MenuItem("Exit");
      exitMI.addActionListener(this);
      fileMenu.add(exitMI);
      mbar.add(fileMenu);
      Menu optMenu = new Menu("Options");
      redMI = new MenuItem("Red starts");
      redMI.addActionListener(this);
      optMenu.add(redMI);
      yellowMI = new MenuItem("Yellow starts");
      yellowMI.addActionListener(this);
      optMenu.add(yellowMI);
      mbar.add(optMenu);
      setMenuBar(mbar);
      Panel panel = new Panel();
      btn1 = new Button("1");
      btn1.addActionListener(this);
      panel.add(btn1);
      lblSpacer = new Label(SPACE);
      panel.add(lblSpacer);
   
      btn2 = new Button("2");
      btn2.addActionListener(this);
      panel.add(btn2);
      lblSpacer = new Label(SPACE);
      panel.add(lblSpacer);
   
      btn3 = new Button("3");
      btn3.addActionListener(this);
      panel.add(btn3);
      lblSpacer = new Label(SPACE);
      panel.add(lblSpacer);
   
      btn4 = new Button("4");
      btn4.addActionListener(this);
      panel.add(btn4);
      lblSpacer = new Label(SPACE);
      panel.add(lblSpacer);
   
      btn5 = new Button("5");
      btn5.addActionListener(this);
      panel.add(btn5);
      lblSpacer = new Label(SPACE);
      panel.add(lblSpacer);
   
      btn6 = new Button("6");
      btn6.addActionListener(this);
      panel.add(btn6);
      lblSpacer = new Label(SPACE);
      panel.add(lblSpacer);
   
      btn7 = new Button("7");
      btn7.addActionListener(this);
      panel.add(btn7);
      add(panel, BorderLayout.NORTH);
      initialize();
      setSize(1024, 768);
   } 

   //creating the array for the template
   public void initialize() {
      array=new int[maxrow][maxcol];
      for (int row=0; row<maxrow; row++)
         for (int col=0; col<maxcol; col++)
            array[row][col]=blank;
      startGame=false;
   }

   //paints the template with graphics
   public void paint(Graphics g) {
      g.setColor(Color.BLUE);
      g.fillRect(110, 50, 100+100*maxcol, 100+100*maxrow);
      for (int row=0; row<maxrow; row++)
         for (int col=0; col<maxcol; col++) {
            if (array[row][col]==blank) g.setColor(Color.WHITE);
            if (array[row][col]==red) g.setColor(Color.red);
            if (array[row][col]==yellow) g.setColor(Color.yellow);
            g.fillOval(160+100*col, 100+100*row, 100, 100);
         }
      check4(g);
   }

   //puts disks into different parts of the connect four template
   public void putDisk(int n) {
      if (end) 
         return;
      startGame=true;
      int row;
      n--;
      for (row=0; row<maxrow; row++)
         if (array[row][n]>0) 
            break;
      if (row>0) {
         array[--row][n]=activeColour;
         if (activeColour==red)
            activeColour=yellow;
         else
            activeColour=red;
         repaint();
      }
   }

   //displays which player wins
   public void displayWinner(Graphics g, int n) {
      g.setColor(Color.BLACK);
      g.setFont(new Font("Courier", Font.BOLD, 100));
      if (n==red)
         g.drawString("Red wins!", 100, 400);
      else
         g.drawString("Yellow wins!", 100, 400);
      end=true;
   }

   //checks if their is a winner and calls to display the winner
   public void check4(Graphics g) {
      for (int row=0; row<maxrow; row++) {
         for (int col=0; col<maxcol-3; col++) {
            int curr = array[row][col];
            if (curr>0
            && curr == array[row][col+1]
            && curr == array[row][col+2]
            && curr == array[row][col+3]) {
               displayWinner(g, array[row][col]);
            }
         }
      }
      for (int col=0; col<maxcol; col++) {
         for (int row=0; row<maxrow-3; row++) {
            int curr = array[row][col];
            if (curr>0
            && curr == array[row+1][col]
            && curr == array[row+2][col]
            && curr == array[row+3][col])
               displayWinner(g, array[row][col]);
         }
      }
      for (int row=0; row<maxrow-3; row++) {
         for (int col=0; col<maxcol-3; col++) {
            int curr = array[row][col];
            if (curr>0
            && curr == array[row+1][col+1]
            && curr == array[row+2][col+2]
            && curr == array[row+3][col+3])
               displayWinner(g, array[row][col]);
         }
      }
      for (int row=maxrow-1; row>=3; row--) {
         for (int col=0; col<maxcol-3; col++) {
            int curr = array[row][col];
            if (curr>0
            && curr == array[row-1][col+1]
            && curr == array[row-2][col+2]
            && curr == array[row-3][col+3])
               displayWinner(g, array[row][col]);
         }
      }
   }
   
   //makes a move if a certain button is pressed
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == btn1)
         putDisk(1);
      else if (e.getSource() == btn2)
         putDisk(2);
      else if (e.getSource() == btn3)
         putDisk(3);
      else if (e.getSource() == btn4)
         putDisk(4);
      else if (e.getSource() == btn5)
         putDisk(5);
      else if (e.getSource() == btn6)
         putDisk(6);
      else if (e.getSource() == btn7)
         putDisk(7);
      else if (e.getSource() == newMI) {
         end=false;
         initialize();
         repaint();
      } 
      else if (e.getSource() == exitMI) {
         System.exit(0);
      } 
      else if (e.getSource() == redMI) {
         if (!startGame) activeColour=red;
      } 
      else if (e.getSource() == yellowMI) {
         if (!startGame) activeColour=yellow;
      }
   } 
}