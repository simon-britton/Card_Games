package deck;

import java.util.Observable;
import java.util.Observer;

import card.Rank;
import deck.Deck;

public class CardSummer implements Observer {
	private Deck deck;
	public CardSummer(){
		
	}
	@Override
	public void update(Observable observable, Object arg1) {
		int sum = 0;
		deck = (Deck) observable;
		for(int i=0; i<deck.size(); i++) {
			if(deck.get(i).getRank() == Rank.JACK || deck.get(i).getRank() == Rank.QUEEN || deck.get(i).getRank() == Rank.KING) {
				sum+=10;
			} else {
				sum+=deck.get(i).getRankValue();
			}
		}
		System.out.println(sum);
	}
}
