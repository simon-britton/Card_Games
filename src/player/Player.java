package player;
import deck.Deck;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import card.Card;

public abstract class Player {
	public Deck MyDeck = new Deck();
	public boolean finished = false;
	abstract public void showHand();
	abstract public void takeTurn(Deck pile, Deck stock, JTextField TopCard, JTextArea moves, Player NextPlayer, JTextField yourmove, JFrame frame);
	public void give(Player p, Card card) {
		p.MyDeck.add(card);
		MyDeck.remove(card);
	}

	public void give(Deck d, Card card) {
		d.add(card);
		MyDeck.remove(card);
	}
}
