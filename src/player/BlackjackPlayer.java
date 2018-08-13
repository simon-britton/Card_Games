package player;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import card.Card;
import deck.Deck;

public class BlackjackPlayer extends Player {
	public BlackjackPlayer() {
		
	}
	
	@Override
	public void showHand() {
		MyDeck.display();
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
	public void takeTurn(Deck pile, Deck stock, JTextField TopCard, JTextArea moves, Player NextPlayer, JTextField yourmove, JFrame frame) {
	}
}
