package deck;
import card.Card;
import player.Player;
import java.util.ArrayList;
import java.util.Random;
import java.util.Observable;

public class Deck extends Observable {
	
	public ArrayList<Card> Cards = new ArrayList<Card>();
	
	public Deck() {
		
	}
	
	public void create() {
	}
	
	public void deal(Player...players) {
	}
	
	public Card get(int i) {
		return(Cards.get(i));
	}
	
	public int size() {
		return(Cards.size());
	}
	
	public Card TopCard() {
		return(Cards.get(Cards.size()-1));
	}
	
	public boolean hasCard(Card card) {
		for(int i=0; i<Cards.size(); i++) {
			if(Cards.get(i).equals(card)) {
				return(true);
			}
		}
		return(false);
	}
	
	public void shuffle() { 
		ArrayList<Card> ShuffledCards = new ArrayList<Card>();
		int savesize = Cards.size();
		Random r1 = new Random();
		int strike=0;
		for(int i=0; i<savesize; i++) {
			strike = r1.nextInt(Cards.size());
			ShuffledCards.add(Cards.get(strike));
			Cards.remove(strike);
		}
		Cards.clear();
		for(int i=0; i<ShuffledCards.size(); i++) {
			Cards.add(ShuffledCards.get(i));
		}
	}
	
	public void add(Card card) {
		Cards.add(card);
		setChanged();
		notifyObservers();
	}
	
	public void remove(Card card) {
		for(int i=0; i<this.size(); i++) {
			if(this.get(i).equals(card)) {
				Cards.remove(i);
				break;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void give(Player p, Card card) {
		p.MyDeck.add(card);
		this.remove(card);
		setChanged();
		notifyObservers();
	}
	
	public void give(Deck d, Card card) {
		d.add(card);
		this.remove(card);
		setChanged();
		notifyObservers();
	}
	
	public void display() {
		for(int i=0; i<Cards.size(); i++) {
			Cards.get(i).print();
		}
		System.out.println();
	}
}
