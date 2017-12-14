import java.util.ArrayList;
import java.util.Collections;



public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        // i is the suit of the card and increases by one every 13 cards created
        for (int i = 0; i <= 3; i++) {
            //type of card, ace, king, jack and queen are handled by the card class
            for (int j = 1; j <= 13; j++) {
                Card e = new Card (j, i);
                deck.add(e);
            }
        }
        shuffle();
    }
    public int deckSize(){
        return deck.size();
    }
    public Card getCard(){
        
        return deck.get(0);
    }
    public Card removeCard(){
        return deck.remove(0);
    }
    public final void shuffle(){
        Collections.shuffle(deck);
    }
}
