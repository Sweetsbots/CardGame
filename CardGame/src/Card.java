public class Card  implements Comparable<Card>{

    private final int Hearts = 0;
    private final int Clubs = 1;
    private final int Diamonds = 2;
    private final int Spades = 3;
    private final int suit;
    private final int value;

    public Card(int cardValue, int type) {
        this.suit = type;
        this.value = cardValue;
    }

    public int getSuit() {
        // Return the int that codes for this card's suit.
        return suit;
    }

    public int getValue() {
        // Return the int that codes for this card's value.
        return value;
    }

    public String getSuitAsString() {
        switch (suit) {
            case Spades:
                return "Spades";
            case Hearts:
                return "Hearts";
            case Diamonds:
                return "Diamonds";
            case Clubs:
                return "Clubs";
            default:
                return "";
        }
    }

    public String getValueAsString() {
        switch (value) {
            case 1:
                return "Ace";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return "??";
        }
    }
    
    @Override
    public String toString() {
        return getValueAsString() + " of " + getSuitAsString();
    }

    @Override
    public int compareTo(Card t) {
        int r = 0;
        if (this.value > t.getValue()) r = 1;
        if (this.value <= t.getValue()) r = -1;
        
        return r;
    }
}
