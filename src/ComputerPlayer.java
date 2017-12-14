import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Sweet
 */
public class ComputerPlayer extends Deck {

    private ArrayList<Card> computerHand = new ArrayList<>();
    private ArrayDeque<Card> discardPile = new ArrayDeque<>();
    private double choice;
    private double discard;

    //creates the computerPlayer and passes the computers hand and the discardpile to it
    public ComputerPlayer(ArrayDeque<Card> discardPile, ArrayList<Card> computerHand) {
        choice = 0;
        discard = 0;
        this.computerHand = computerHand;
        this.discardPile = discardPile;
    }

    //the original discard method, was completely random, replaced with the smartDiscard
    //method as an extra credit attempt
//    public Card discard() {
//        discard = Math.ceil(Math.random() * 100);
//        Card c = new Card(0, 0);
//        if (discard <= 20) {
//            c = computerHand.remove(0);
//        } else if (discard > 20 && discard <= 40) {
//            c = computerHand.remove(1);
//        } else if (discard > 40 && discard <= 60) {
//            c = computerHand.remove(2);
//        } else if (discard > 60 && discard <= 80) {
//            c = computerHand.remove(3);
//        } else if (discard > 80 && discard <= 100) {
//            c = computerHand.remove(4);
//        }
//        return c;
//    }

    //the original choice method, method was random based off what number the computer 
    //generated. replaced by smart choice
//    public int choice() {
//        this.choice = Math.ceil(Math.random() * 100);
//        int c = 0;
//        if (this.choice < 50) {
//            c = 1;
//        } else {
//            c = 2;
//        }
//        return c;
//    }

    //has the computer smartly choose whether to pick up the card from the discard
    //pile or pick up from the deck
    public int smartChoice() {
        HashMap<Integer, Integer> valueFrequency = new HashMap<>();
        //gets the frequency of values in the computers hand
        for (int i = 0; i < computerHand.size(); i++) {
            Integer freq = valueFrequency.get(computerHand.get(i).getValue());
            if (freq == null) {
                freq = 1;
            } else {
                freq++;
            }
            valueFrequency.put(computerHand.get(i).getValue(), freq);
        }
        //compares the values in the hand
        for (int i = 0; i < computerHand.size(); i++) {
            //checks if the value on the discard pile exists in the hand, if it doesnt, makes the computer draw a new card
            if (valueFrequency.containsKey(discardPile.peek().getValue())) {
                //checks to see if the value in the discard pile is the most contained value
                if (valueFrequency.get(computerHand.get(i).getValue()) >= valueFrequency.get(discardPile.peek().getValue())) {
                    return 2;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
        return 1;
    }

    public Card smartDiscard() {
        //sorts the computer hand for easier searching
        Collections.sort(computerHand);
        int index = 0;
        int min = 10;
        //picks the card to do comparisons with
        for (int j = 0; j < computerHand.size(); j++) {
            //sets the card value to the j
            int value = computerHand.get(j).getValue();
            int count = 1;
            //compares the j valued card to the rest of the cards in the hand
            for (int i = computerHand.size() - 1; i >= 0; i--) {
                //since count starts at 1, it ignores itself
                if (i == j) {
                } else {
                    //if the i value card is equal to the j value card, increases the count
                    if (computerHand.get(i).getValue() == value) {
                        count++;
                    }
                }
            }
            //since at the beginning, all cards could only appear once, minimum is set higher
            //than the amount of cards so the first card is automatically the minimum even if there 
            //is more than one of that card, 
            if (count < min) {
                min = count;
                index = j;
            }
        }
        //removes the card that appears the least times in the hand
        return computerHand.remove(index);
    }
}
