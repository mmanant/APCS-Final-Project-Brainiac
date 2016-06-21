/**
 * Board.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the window that the cards are shown on and deals with the  
 * relationship between the two cards that are chosen.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Board extends JFrame{
   private List<Card> cards;
   private Card selectedCard;
   private Card a;
   private Card b;
   private Timer t;

   //constuctor that makes each card and their ids, creates the JFrame and gives basic functions
   public Board(){
      int pairs = 10;
      List<Card> cardsList = new ArrayList<Card>();
      List<Integer> cardVals = new ArrayList<Integer>();
      for (int i = 0; i < pairs; i++){
         cardVals.add(i);
         cardVals.add(i);
      }
      Collections.shuffle(cardVals);
      for (int val : cardVals){
      Card c = new Card();
         c.setId(val);
         c.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  
                  selectedCard = (Card) ae.getSource();
                  doTurn();
               }
            });
         cardsList.add(c);
      }
      this.cards = cardsList;
      t = new Timer(750, 
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               checkCards();
            }
         });
   
      t.setRepeats(false);
      Container pane = getContentPane();
      pane.setLayout(new GridLayout(4, 5));
      for (Card c : cards){
         pane.add(c);
      }
      setTitle("Memory Match");
   }


   //shows ids of cards and keeps them either turned over or 
   //starts from the beginning
   public void doTurn(){
      if (a == null && b == null){
         a = selectedCard;
         a.setText(String.valueOf(a.getId()));
      }
   
      if (a != null && a != selectedCard && b == null){
         b = selectedCard;
         b.setText(String.valueOf(b.getId()));
         t.start();
      }
   }

   //checks if cards all cards are matched or returns to original text if pairs do not match
   public void checkCards(){
      if (a.getId() == b.getId()){
         a.setEnabled(false); 
         b.setEnabled(false);
         a.setMatched(true); 
         b.setMatched(true);
         if (this.isGameWon()){
            JOptionPane.showMessageDialog(this, "You win!");
            System.exit(0);
         }
      }
      
      else{
         a.setText(""); 
         b.setText("");
      }
      a = null; 
      b = null;
   }

   //checks if all cards are matched and return true if true
   public boolean isGameWon(){
      for(Card c: this.cards){
         if (c.getMatched() == false){
            return false;
         }
      }
      return true;
   }

}