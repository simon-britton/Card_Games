package card;

public enum Rank {
	HARDACE(1), SOFTACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
	
	private final int value;
	
	private Rank(int value){
		this.value = value;
	}
	
	public int getRankValue() {
		return value;
	}
	
	@Override
	public String toString() {
		switch(this) {
			case HARDACE:
				return "Ace";
			case SOFTACE:
				return "Ace";
			case TWO:
				return "Two";
			case THREE:
				return "Three";
			case FOUR:
				return "Four";
			case FIVE:
				return "Five";
			case SIX:
				return "Six";
			case SEVEN:
				return "Seven";
			case EIGHT:
				return "Eight";
			case NINE:
				return "Nine";
			case TEN:
				return "Ten";
			case JACK:
				return "Jack";
			case QUEEN:
				return "Queen";
			case KING:
				return "King";
			default:
				return "";
		}
	}
}
