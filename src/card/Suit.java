package card;

public enum Suit {
	DIAMONDS(Color.RED), HEARTS(Color.RED), SPADES(Color.BLACK), CLUBS(Color.BLACK);
	
	enum Color{
		RED, BLACK;
		@Override
		public String toString() {
			switch(this) {
				case RED:
					return "Red";
				case BLACK:
					return "Black";
				default:
					return"";
			}
		}
	}
	
	private final Color color;
	
	private Suit(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		switch(this) {
			case DIAMONDS:
				return "Diamonds";
			case HEARTS:
				return "Hearts";
			case SPADES:
				return "Spades";
			case CLUBS:
				return "Clubs";
			default:
				return "";
		}
	}
}
