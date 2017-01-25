/**
 * Brain.java

 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the first window that pops up with all of the lobes of the brain 
 * and has different buttons you can click corresponding with each lobe.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */


/* TEST
 * TEST
 * TEST
 * TEST
 * TEST
 * TEST
 * TEST
 * TEST 
 * TEST
 * TEST
 * TEST
 * TEST
 * TEST
 * TEST
 * TEST
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
public class Brain extends JComponent
///TEST333333444////
{
   private Image brain;
   private JFrame frame;
   private Dimension dimension;

   //constructor for program that makes a JFrame
   public Brain(){  
      dimension = new Dimension(15, 15);
      frame = new JFrame("Brain");
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(this);
      frame.setBounds(600, 0, 550, 420);
      frame.setVisible(true);
   }

   //makes a main method which creates opening screen, JButtons for each lobe, and actionListeners
   public static void main(String[] args) throws IOException {
   
      JFrame frame = new JFrame("Brainiac");
      frame.setContentPane(
            new JPanel() {
               BufferedImage image =  ImageIO.read(new File("Brain.jpg"));              
               public void paintComponent(Graphics g) {
                  super.paintComponent(g);
                  g.drawImage(image, 0, 0, 550, 410, this);
               }
            });
      JButton parietal = new JButton("Parietal Lobe");
      parietal.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  Parietal pariet = new Parietal();
               }
            });
   
      JButton temporal =new JButton("Temporal Lobe");
      temporal.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  Temporal temp = new Temporal();
               }
            });
   
      JButton frontal =new JButton("Frontal Lobe");
      frontal.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  Frontal front = new Frontal();
               }
            });
      JButton occipital =new JButton("Occipital Lobe");
      occipital.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  Occipital occip = new Occipital();
               }
            });
      frame.add(frontal);
      frame.add(temporal);
      frame.add(parietal);
      frame.add(occipital);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(550, 425);
      frame.setVisible(true);
   }
}