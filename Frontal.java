/**
 * Frontal.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the window that pops up with an information about the frontal lobe, 
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
public class Frontal extends JFrame
{
   private Image frontal;
   private Dimension dimension;
   private JLabel background;
   private JButton playgame;

   //creates a JFrame for showing information about the frontal lobe and contains actionListener for "Play Game"
   public Frontal(){  
      new JFrame("Frontal Lobe");
      dimension = new Dimension(15, 15);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      background = new JLabel(new ImageIcon("Frontal.jpg"));
      add(background, BorderLayout.CENTER);
      playgame = new JButton("Play Game");
      add(playgame, BorderLayout.PAGE_END);
      playgame.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  Board b = new Board();
                  b.setPreferredSize(new Dimension(500,500));
                  b.setLocation(500, 250);
                  b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  b.pack();
                  b.setVisible(true);
               
               }
            });
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(700, 550);
      setVisible(true);
   }   
}