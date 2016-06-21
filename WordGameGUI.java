 /**
 * WordGameGUI.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the window and GUI that pops up and is visible to the user 
 * and is used by the WordGame.java.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WordGameGUI {
   public static JFrame frame;
   public static JTextField textField;
   public static JLabel guessesRemainingLabel;
   public static JLabel lettersGuessedLabel;
   public static JLabel wordLabel;

   //builds the GUI
   public static void buildGUI(){
      SwingUtilities.invokeLater(
                    new Runnable(){
                       public void run(){
                          frame = new JFrame("WordGame");
                          guessesRemainingLabel = new JLabel("Guesses remaining: " + String.valueOf(WordGame.guessesRemaining));
                          lettersGuessedLabel = new JLabel("Already guessed: ");
                          wordLabel = new JLabel();
                          textField = new JTextField();
                          JButton checkButton = new JButton("Guess");
                          GuessListener guessListener = new GuessListener();
                          checkButton.addActionListener(guessListener);
                          textField.addActionListener(guessListener);
                          JPanel labelPanel = new JPanel();
                          labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
                          labelPanel.add(guessesRemainingLabel);
                          labelPanel.add(lettersGuessedLabel);
                          labelPanel.add(wordLabel);
                          JPanel userPanel = new JPanel(new BorderLayout());
                          userPanel.add(BorderLayout.CENTER, textField);
                          userPanel.add(BorderLayout.EAST, checkButton);
                          labelPanel.add(userPanel);
                          frame.add(BorderLayout.CENTER, labelPanel);
                       
                          frame.setSize(250, 100);
                          frame.setLocationRelativeTo(null);
                          frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                          frame.setVisible(true);
                       }
                    }
         );
   }

   //outputs the guesses remaining
   public static void drawGuessesRemaining(){
      final String guessesMessage = "Guesses remaining: " + String.valueOf(WordGame.guessesRemaining);
      SwingUtilities.invokeLater(
                    new Runnable(){
                       public void run(){
                          guessesRemainingLabel.setText(guessesMessage);
                          guessesRemainingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                       }
                    }
         );
   }

   //outputs the letters guessed
   public static void drawLettersGuessed(){
      StringBuilder lettersBuilder = new StringBuilder();
      for (Character el : WordGame.lettersGuessed) {
         String s = el + " ";
         lettersBuilder.append(s);
      }
   
      final String letters = lettersBuilder.toString();
      SwingUtilities.invokeLater(
                    new Runnable() {
                    
                       public void run() {
                          lettersGuessedLabel.setText("Already guessed: " + letters);
                       }
                    }
         );
   }

   //draws the secret word with dashes & etc for user to use to guess the word with
   public static void drawSecretWord(){
      StringBuilder word = new StringBuilder();
      for(int i=0; i<WordGame.lettersRevealed.length; i++){
      
         if (WordGame.lettersRevealed[i]) {
            String s = WordGame.word.charAt(i) + " ";
            word.append(s);
         } 
         else {
            word.append("_ ");
         }
      }
   
      final String w = word.toString();
      SwingUtilities.invokeLater(
                    new Runnable(){
                    
                       public void run() {
                          wordLabel.setText(w);
                       }
                    }
         );
   }

   //shows the confirm 
   public static int playAgainDialog(String m){
      return JOptionPane.showConfirmDialog(frame, m, "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
   }


   //returns the text the user inputs
   public static String getText(){
      return textField.getText();
   }

   //sets the text the user inputs
   public static void setText(final String t){
      SwingUtilities.invokeLater(
                    new Runnable() {
                    
                       public void run() {
                          textField.setText(t);
                       }
                    }
         );
   }


   //checks if users guess is correct and updates word
   public static class GuessListener implements ActionListener {
      public void actionPerformed(ActionEvent ev){
         String guess = getText();
      
         if(WordGame.checkUserGuess(guess)) {
            WordGame.updateSecretWord(guess);
            drawSecretWord();
         
            if(WordGame.lettersGuessed.size() != 0)      
               drawLettersGuessed();
            if (WordGame.checkIfWon())
               WordGame.winSequence();
            else if (WordGame.guessesRemaining == 0)
               WordGame.loseSequence();
         }
      }
   }
}