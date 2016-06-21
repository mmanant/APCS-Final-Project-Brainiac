/**
 * TicTacToe.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the tic tac toe game and includes functions such as changing line thickness and colors, 
 * drawing the board GUI and controlling the computers input in the game.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */
 
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

public class TicTacToe extends JFrame implements ChangeListener, ActionListener {
   public JSlider slider;
   public JButton Obutton, Xbutton;
   public Board board;
   public int lineThickness=4;
   public Color oColor=Color.BLUE, xColor=Color.RED;
   static final char BLANK=' ', O='O', X='X';
   public char position[]={BLANK, BLANK, BLANK,BLANK, BLANK, BLANK,BLANK, BLANK, BLANK};
   public int wins=0, losses=0, draws=0;

   // Constructer for TicTacToe board which creates JPanel, adds Buttons and actionListeners
   public TicTacToe() {
      super("Tic Tac Toe");
      JPanel topPanel=new JPanel();
      topPanel.setLayout(new FlowLayout());
      topPanel.add(new JLabel("Line Thickness:"));
      topPanel.add(slider=new JSlider(SwingConstants.HORIZONTAL, 1, 20, 4));
      slider.setMajorTickSpacing(1);
      slider.setPaintTicks(true);
      slider.addChangeListener(this);
      topPanel.add(Obutton=new JButton("O Color"));
      topPanel.add(Xbutton=new JButton("X Color"));
      Obutton.addActionListener(this);
      Xbutton.addActionListener(this);
      add(topPanel, BorderLayout.NORTH);
      add(board=new Board(), BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(500, 500);
      setVisible(true);
   }

   //changes line thickness
   public void stateChanged(ChangeEvent e) {
      lineThickness = slider.getValue();
      board.repaint();
   }

   //changes color of O or X
   public void actionPerformed(ActionEvent e) {
      if (e.getSource()==Obutton) {
         Color newColor = JColorChooser.showDialog(this, "Choose a new color for O", oColor);
         if (newColor!=null)
            oColor=newColor;
      }
      else if (e.getSource()==Xbutton) {
         Color newColor = JColorChooser.showDialog(this, "Choose a new color for X", xColor);
         if (newColor!=null)
            xColor=newColor;
      }
      board.repaint();
   }

   // Board is what actually plays and displays the game
   public class Board extends JPanel implements MouseListener {
      public Random random=new Random();
      public int rows[][]={{0,2},{3,5},{6,8},{0,6},{1,7},{2,8},{0,8},{2,6}};
   
      //constructor for Board for TicTacToe
      public Board() {
         addMouseListener(this);
      }
   
      //draw the board with updates
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         int w=getWidth();
         int h=getHeight();
         Graphics2D g2d = (Graphics2D) g;
         g2d.setPaint(Color.WHITE);
         g2d.fill(new Rectangle2D.Double(0, 0, w, h));
         g2d.setPaint(Color.BLACK);
         g2d.setStroke(new BasicStroke(lineThickness));
         g2d.draw(new Line2D.Double(0, h/3, w, h/3));
         g2d.draw(new Line2D.Double(0, h*2/3, w, h*2/3));
         g2d.draw(new Line2D.Double(w/3, 0, w/3, h));
         g2d.draw(new Line2D.Double(w*2/3, 0, w*2/3, h));
         for (int i=0; i<9; ++i) {
            double x=(i%3+0.5)*w/3.0;
            double y=(i/3+0.5)*h/3.0;
            double xr=w/8.0;
            double yr=h/8.0;
            if (position[i]==O) {
               g2d.setPaint(oColor);
               g2d.draw(new Ellipse2D.Double(x-xr, y-yr, xr*2, yr*2));
            }
            else if (position[i]==X) {
               g2d.setPaint(xColor);
               g2d.draw(new Line2D.Double(x-xr, y-yr, x+xr, y+yr));
               g2d.draw(new Line2D.Double(x-xr, y+yr, x+xr, y-yr));
            }
         }
      }
   
      //draw an O where the mouse is clicked
      public void mouseClicked(MouseEvent e) {
         int x=e.getX()*3/getWidth();
         int y=e.getY()*3/getHeight();
         int pos=x+3*y;
         if (pos>=0 && pos<9 && position[pos]==BLANK) {
            position[pos]=O;
            repaint();
            putX();  // computer plays
            repaint();
         }
      }
   
      // Ignore other mouse events
      public void mousePressed(MouseEvent e) {}
      public void mouseReleased(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}
   
      //computer plays game as X
      public void putX() {
         if (won(O))
            newGame(O);
         else if (isDraw())
            newGame(BLANK);
         else {
            nextMove();
            if (won(X))
               newGame(X);
            else if (isDraw())
               newGame(BLANK);
         }
      }
   
      //return true if player has won
      public boolean won(char player) {
         for (int i=0; i<8; ++i)
            if (testRow(player, rows[i][0], rows[i][1]))
               return true;
         return false;
      }
   
      //checks whether the player won in the row from position[a] to position[b]
      public boolean testRow(char player, int a, int b) {
         return position[a]==player && position[b]==player 
              && position[(a+b)/2]==player;
      }
   
      //plays X in the next spot
      public void nextMove() {
         int r=findRow(X);  // complete a row of X and win if possible
         if (r<0)
            r=findRow(O);  // or try to block O from winning
         if (r<0) {  // otherwise move randomly
            do
               r=random.nextInt(9);
             while (position[r]!=BLANK);
         }
         position[r]=X;
      }
   
      //returns 0-8 for the position of a blank spot in a row if the
      //other 2 spots are occupied by player, or -1 if no spot exists
      public int findRow(char player) {
         for (int i=0; i<8; ++i) {
            int result=find1Row(player, rows[i][0], rows[i][1]);
            if (result>=0)
               return result;
         }
         return -1;
      }
   
      //if 2 of 3 spots in the row from position[a] to position[b]
      //are occupied by player and the third is blank, then return the
      //index of the blank spot, else return -1.
      public int find1Row(char player, int a, int b) {
         int c=(a+b)/2;  // middle spot
         if (position[a]==player && position[b]==player && position[c]==BLANK)
            return c;
         if (position[a]==player && position[c]==player && position[b]==BLANK)
            return b;
         if (position[b]==player && position[c]==player && position[a]==BLANK)
            return a;
         return -1;
      }
   
      //checks if all 9 spots filled
      public boolean isDraw() {
         for (int i=0; i<9; ++i)
            if (position[i]==BLANK)
               return false;
         return true;
      }
   
      //starts a new game and shows statistics
      public void newGame(char winner) {
         repaint();
      
         String result;
         if (winner==O) {
            ++wins;
            result = "You Win!";
         }
         else if (winner==X) {
            ++losses;
            result = "I Win!";
         }
         else {
            result = "Tie";
            ++draws;
         }
         if (JOptionPane.showConfirmDialog(null, 
              "You have "+wins+ " wins, "+losses+" losses, "+draws+" draws\n"
              +"Play again?", result, JOptionPane.YES_NO_OPTION)
              !=JOptionPane.YES_OPTION) {
            System.exit(0);
         }
         for (int j=0; j<9; ++j)
            position[j]=BLANK;
         if ((wins+losses+draws)%2 == 1)
            nextMove();
      }
   }
} 