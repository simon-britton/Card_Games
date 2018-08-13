package card;
import card.Rank;
import card.Suit;
import card.Suit.Color;

public class Card {
	Suit suit;
	Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public void print() {
		System.out.println(rank.toString()+" "+"of"+" "+suit.toString());
	}
	
	@Override
	public String toString() {
		return(rank.toString()+" "+"of"+" "+suit.toString());
	}
	
	public Color getColor() {
		return suit.getColor();
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public int getRankValue() {
		return rank.getRankValue();
	}
	
	public boolean equals(Card card) {
		if(this.rank == card.rank && this.suit == card.suit) {
			return(true);
		} else {
			return(false);
		}
	}
}
