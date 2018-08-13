package deck;

import static java.util.EnumSet.complementOf;

import java.util.EnumSet;

import card.Card;
import card.Rank;
import card.Suit;

import player.Player;

public class BlackjackDeck extends Deck{
	private Card a1 = new Card(Suit.DIAMONDS, Rank.SOFTACE);
	private Card a2 = new Card(Suit.HEARTS, Rank.SOFTACE);
	private Card a3 = new Card(Suit.SPADES, Rank.SOFTACE);
	private Card a4 = new Card(Suit.CLUBS, Rank.SOFTACE);
	
	public BlackjackDeck(){
	}
	
	@Override
	public void shuffle() {
		Cards.remove(a1);
		Cards.remove(a2);
		Cards.remove(a3);
		Cards.remove(a4);
		super.shuffle();
		Cards.add(a1);
		Cards.add(a2);
		Cards.add(a3);
		Cards.add(a4);
	}
	
	public Card get(int i) {
		return(super.get(i));
	}
	
	@Override
	public void display() {
		Cards.remove(a1);
		Cards.remove(a2);
		Cards.remove(a3);
		Cards.remove(a4);
		super.display();
		Cards.add(a1);
		Cards.add(a2);
		Cards.add(a3);
		Cards.add(a4);
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
		Cards.add(a1);
		Cards.add(a2);
		Cards.add(a3);
		Cards.add(a4);
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
	public void give(Deck d, Card card) {
		super.give(d, card);
	}
	
	@Override
	public boolean hasCard(Card card) {
		return(super.hasCard(card));
	}
	
	@Override
	public void deal(Player...players) {
		for(Player p: players) {
			for(int i=0; i<2; i++) {
				this.give(p, this.get(0));
			}
		}
		setChanged();
		notifyObservers();
	}
}
