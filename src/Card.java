public class Card  implements Comparable<Card>{

	private final int hearts = 0;
	private final int clubs = 1;
	private final int diamonds = 2;
	private final int spades = 3;
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
		case spades:
			return "Spades";
		case hearts:
			return "Hearts";
		case diamonds:
			return "Diamonds";
		case clubs:
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
	public int compareTo(Card type) {
		if (this.value > type.getValue()) {
			return 1;
		}
		if (this.value <= type.getValue()) {
			return -1;
		}
		return 1;
	}
}
