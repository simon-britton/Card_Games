package deck;

import java.util.EnumSet;
import static java.util.EnumSet.complementOf;

import card.Card;
import card.Suit;
import card.Rank;
import player.Player;

public class CrazyEightsDeck extends Deck {

	public CrazyEightsDeck(){
	}
	
	@Override
	public void shuffle() {
		super.shuffle();
	}
	
	@Override
	public Card get(int i) {
		return(super.get(i));
	}
	
	@Override 
	public void give(Deck d, Card card) {
		super.give(d, card);
	}
	
	@Override
	public boolean hasCard(Card card) {
		return(super.hasCard(card));
	}
	
	@Override
	public void display() {
		super.display();
	}
	
	@Override
	public void create() {
		for(Suit suit: Suit.values()) {
			for(Rank rank: complementOf(EnumSet.of(Rank.SOFTACE))) {
				Cards.add(new Card(suit, rank));
			}
		}
		setChanged();
		notifyObservers();
	}
	
	@Override
	public void add(Card card) {
		super.add(card);
	}
	
	@Override
	public void remove(Card card) {
		super.remove(card);
	}
	
	@Override
	public void give(Player p, Card card) {
		super.give(p, card);
	}
	
	@Override
	public Card TopCard() {
		return(super.TopCard());
	}

	@Override
	public void deal(Player... players) {
		for(Player p: players) {
			for(int i=0; i<5; i++) {
				this.give(p, this.get(0));
			}
		}
		setChanged();
		notifyObservers();
	}
}
