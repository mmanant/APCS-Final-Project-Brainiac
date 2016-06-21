/**
 * WordGame.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the arrays necessary to display on the window and checks 
 * if different conditions are true, such as if the user won or if their guess of the letter was correct
 * and uses WordGameGUI.java.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */
 
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class WordGame {
   public static String[] wordList;
   public static String word;
   public static Set<Character> alphabet;
   public static Set<Character> lettersGuessed;    
   public static boolean[] lettersRevealed;       
   public static int guessesRemaining;

   //sees if the user has won the game
   public static boolean checkIfWon(){
      for(boolean isLetterShown : lettersRevealed){
         if(!isLetterShown)
            return false;
      }
      return true;
   }

   //get input from the user
   public static boolean checkUserGuess(String l){
      if(l.length() == 1 && !lettersGuessed.contains(l.charAt(0)) && alphabet.contains(l.charAt(0))) {
         WordGameGUI.setText(null);
         lettersGuessed.add(l.charAt(0));
         return true;
      }
      else{
         Toolkit.getDefaultToolkit().beep();
      }
      return false;
   }

   //selects a word
   public static String chooseSecretWord(String[] wordList){
      return wordList[(int)(Math.random() * wordList.length)];
   }

   //creates the alphabet set that's used to ensure that the user's guess not a number nor a special character
   public void createAlphabetSet(){
      alphabet = new HashSet<Character>(26);
      for(Character c = 'a'; c<='z'; c++){
         alphabet.add(c);
      }
   }

   //when the the user runs out of guesses
   public static void loseSequence(){
      for(int i = 0; i < lettersRevealed.length; i++){
         lettersRevealed[i] = true;
      }
      WordGameGUI.drawSecretWord();
      playAgain("Tough luck. The secret word was " + word + ".\nWould you like to play another game of hangman?");
   }

   //allows the user to play another game 
   public static void playAgain(String message){
      int ans = WordGameGUI.playAgainDialog(message);
      if(ans == JOptionPane.YES_OPTION){
         setUpGame();
      }
      else{
         System.exit(0);
      }
   }

   //reads in wordList
   public String[] readFile(String loc){
      BufferedReader input = null;
      try{
         input = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(loc)));
         wordList = input.readLine().split(" ");
      }
      catch(IOException ioException) {
         ioException.printStackTrace();
      }
      finally{
         try {
            if (input != null) input.close();
         }
         catch(IOException ioEx){
            ioEx.printStackTrace();
         }
      }
      return wordList;
   }

   //sets up the variables appropriately
   public static void setUpGame(){
      guessesRemaining = 5;
      word = chooseSecretWord(wordList);
      lettersRevealed = new boolean[word.length()];
      Arrays.fill(lettersRevealed, false);
      lettersGuessed = new HashSet<Character>(26);     
   
      WordGameGUI.drawSecretWord();
      WordGameGUI.drawLettersGuessed();
      WordGameGUI.drawGuessesRemaining();
   }

   //updates which letters of the secret word have been revealed
   public static void updateSecretWord(String l){
      List<Integer> changeBool = new ArrayList<Integer>();
   
      if(word.contains(l)){
         for(int i=0; i<word.length(); i++){
            if(word.charAt(i) == l.charAt(0))
               changeBool.add(i);
         }
         for(Integer idx : changeBool)
            lettersRevealed[idx] = true;
      }
      else{
         guessesRemaining--;
         WordGameGUI.drawGuessesRemaining();
      }
   }

   //when the user has correctly guessed the secret word
   public static void winSequence(){
      playAgain("Well done! You guessed " + word + " with " + guessesRemaining + " guesses left!\n" +
             "Would you like to play another game of hangman?");
   }

}