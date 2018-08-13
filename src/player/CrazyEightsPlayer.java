package player;

import card.Card;
import deck.Deck;
import card.Rank;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CrazyEightsPlayer extends Player {
	int n;
	public CrazyEightsPlayer(int n) {
		this.n=n;
	}
	
	@Override
	public void showHand() {
		super.MyDeck.display();
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
		boolean hasPlayed = false;
		if(finished == true) {
			NextPlayer.finished = true;
		}
		if(finished == false) {
			while(hasPlayed == false) {
				Random r1 = new Random();
				MyDeck.shuffle();
				for(int i=0; i<MyDeck.size(); i++) {
					System.out.println("Suit of Card in MyDeck: "+MyDeck.get(i).getSuit().toString());
					System.out.println("Top Card's Text: "+TopCard.getText());
					System.out.println("Rank of Card in MyDeck: "+MyDeck.get(i).getRank());
					System.out.println("Check Rank Conditional: " + (MyDeck.get(i).getRank() != Rank.EIGHT));
					System.out.println("Check Top Card's Text Conditional: "+(MyDeck.get(i).getSuit().toString().equals(TopCard.getText())));
					System.out.println("MyDeck's Size Is Greater Than Zero"+(MyDeck.size()>0));
					if(MyDeck.get(i).getSuit().toString().equals(TopCard.getText()) && MyDeck.get(i).getRank() != Rank.EIGHT && MyDeck.size()>0) {
						moves.append("Player "+n+" played "+MyDeck.get(i).toString()+"."+"\n");
						TopCard.setText(MyDeck.get(i).toString());
						frame.revalidate();
						MyDeck.give(pile, MyDeck.get(i));
						if(MyDeck.size() == 0) {
							hasPlayed = true;
							moves.append("Player "+n+" wins.");
							frame.revalidate();
							NextPlayer.finished = true;
							yourmove.setEnabled(false);
							break;
						}
						hasPlayed = true;
						break;
					}
				}
				for(int i=0; i<MyDeck.size(); i++) {
					if(hasPlayed == false && MyDeck.get(i).getRank() != Rank.EIGHT && (MyDeck.get(i).getSuit() == pile.TopCard().getSuit()||MyDeck.get(i).getRank() == pile.TopCard().getRank())&&!TopCard.getText().equals("Spades")&&!TopCard.getText().equals("Diamonds")&&!TopCard.getText().equals("Clubs")&&!TopCard.getText().equals("Hearts")&&MyDeck.size()>0) {
						moves.append("Player "+n+" played "+MyDeck.get(i).toString()+"."+"\n");
						TopCard.setText(MyDeck.get(i).toString());
						frame.revalidate();
						MyDeck.give(pile, MyDeck.get(i));
						if(MyDeck.size() == 0) {
							hasPlayed = true;
							moves.append("Player "+n+" wins.");
							frame.revalidate();
							NextPlayer.finished = true;
							yourmove.setEnabled(false);
							frame.revalidate();
							break;
						}
						hasPlayed = true;
						break;
					}
				}
				for(int i = 0; i<MyDeck.size(); i++) {
					if(hasPlayed == false && MyDeck.get(i).getRank() == Rank.EIGHT && MyDeck.size()>0) {
						int choose = r1.nextInt(4);
						MyDeck.give(pile, MyDeck.get(i));
						if(MyDeck.size() == 0) {
							hasPlayed = true;
							moves.append("Player "+n+" wins.");
							frame.revalidate();
							NextPlayer.finished = true;
							yourmove.setEnabled(false);
							frame.revalidate();
							break;
						}
						switch(choose) {
							case 0: TopCard.setText("Clubs");
									moves.append("Player "+n+" chose Clubs."+"\n");
									frame.revalidate();
									break;
							case 1: TopCard.setText("Spades");
									moves.append("Player "+n+" chose Spades."+"\n");
									frame.revalidate();
									break;
							case 2: TopCard.setText("Diamonds");
									moves.append("Player "+n+" chose Diamonds."+"\n");
									frame.revalidate();
									break;
							case 3: TopCard.setText("Hearts");
									moves.append("Player "+n+" chose Hearts."+"\n");
									frame.revalidate();
									break;
							default: moves.append("Pandering"+"\n");
									frame.revalidate();
						}
						hasPlayed = true;
						break;
					}
				}
				int failedcards=0;
				for(int i=0; i<MyDeck.size(); i++) {
					if(hasPlayed == false && MyDeck.get(i).getSuit() != pile.TopCard().getSuit() && MyDeck.get(i).getRank() != pile.TopCard().getRank()&&MyDeck.get(i).getRank() != Rank.EIGHT&&!MyDeck.get(i).getSuit().toString().equals(TopCard.getText().toUpperCase())&&MyDeck.size()!=0) {
						failedcards++;
					}
				}
				int c=0;
				if(failedcards == MyDeck.size()&&MyDeck.size()!=0 && hasPlayed == false) {
					while(MyDeck.get(MyDeck.size()-1).getSuit() != pile.TopCard().getSuit()&&MyDeck.get(MyDeck.size()-1).getRank() != pile.TopCard().getRank()&&MyDeck.get(MyDeck.size()-1).getRank() != Rank.EIGHT&&!MyDeck.get(MyDeck.size()-1).getSuit().toString().equals(TopCard.getText())) {
						if(stock.size() == 0) {
							for(int i=0; i<pile.size()-1; i++) {
								pile.give(stock, pile.get(i));
							}
							stock.shuffle();
						}
						stock.give(MyDeck, stock.TopCard());
						c++;
					}
					if(c==1) {
						moves.append("Player "+n+" drew a card"+"\n");
						frame.revalidate();
					} else {
						moves.append("Player "+n+" drew "+c+" cards."+"\n");
						frame.revalidate();
					}
				} else {
					System.out.println("Top Card:" + pile.TopCard());
					System.out.println("Top Card's Text:" + TopCard.getText());
				}
			}
		}
	}
}
