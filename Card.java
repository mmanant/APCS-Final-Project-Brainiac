/**
 * Card.java
 * Assignment: APCS Final Project 
 *
 * Overview: This class creates the Card object and has different functions 
 * necessary for the use of this object.
 *
 * @version 06/20/16
 * @author Manjari Anant
 */
 
import javax.swing.JButton;

public class Card extends JButton{
    private int id;
    private boolean match = false;

    //sets the number shown on the card
    public void setId(int id){
        this.id = id;
    }

    //returns the number shown on the card
    public int getId(){
        return this.id;
    }

    //sets whether cards are matched or not
        public void setMatched(boolean matched){
        this.match = matched;
    }
    
    //returns whether cards are matched or not
    public boolean getMatched(){
        return this.match;
    }
}