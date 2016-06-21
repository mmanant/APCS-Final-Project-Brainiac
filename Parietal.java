/**
 * Parietal.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the window that pops up with an information about the parietal lobe, 
 * a description of the game that goes with it, and a button "Play Game" to play the game.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */
 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
public class Parietal extends JFrame
{
   private Image parietal;
   private Dimension dimension;
   private JLabel background;
   private JButton playgame;

   //creates a JFrame for showing information about the parietal lobe and contains actionListener for "Play Game"
   public Parietal()
   {  
      new JFrame("Parietal Lobe");
      dimension = new Dimension(15, 15);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      background = new JLabel(new ImageIcon("Parietal.jpg"));
      add(background, BorderLayout.CENTER);
      playgame = new JButton("Play Game");
      add(playgame, BorderLayout.PAGE_END);
      playgame.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  TicTacToe ttt = new TicTacToe();
               }
            });
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(700, 550);
      setVisible(true);
   }   
}