package cardgame;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import player.CrazyEightsPlayer;
import deck.CrazyEightsDeck;
import deck.Deck;

public class CrazyEightsGame implements CardGame {
	CrazyEightsDeck pile = new CrazyEightsDeck();
	Deck stock = new Deck();
	CrazyEightsPlayer User = new CrazyEightsPlayer(0);
	CrazyEightsPlayer Player1 = new CrazyEightsPlayer(1);
	CrazyEightsPlayer Player2 = new CrazyEightsPlayer(2);
	boolean done = false;

	public CrazyEightsGame() {

	}

	public void initialize() {
		pile.create();
		do {
			pile.shuffle();
		} while (pile.TopCard().toString().equals("Eight of Diamonds")
				|| pile.TopCard().toString().equals("Eight of Spades")
				|| pile.TopCard().toString().equals("Eight of Hearts")
				|| pile.TopCard().toString().equals("Eight of Clubs"));
		pile.deal(User, Player1, Player2);
		for (int i = 0; i < pile.size() - 1; i++) {
			pile.give(stock, pile.get(0));
		}
	}

	public void show() {
		this.initialize();
		JFrame frame = new JFrame("Crazy Eights");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);

		JLabel TopCard = new JLabel("Top Card: ");
		JTextField topcard = new JTextField(pile.TopCard().toString(), 15);
		JLabel YourCards = new JLabel("Your Cards: ");
		ArrayList<JTextField> yourcards = new ArrayList<JTextField>();
		JLabel YourMove = new JLabel("Your Move: ");
		JTextField yourmove = new JTextField(15);
		JTextArea moves = new JTextArea(3, 22);
		JScrollPane scroll = new JScrollPane(moves);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		for (int i = 0; i < User.MyDeck.size(); i++) {
			yourcards.add(i, null);
		}
		for (int i = 0; i < User.MyDeck.size(); i++) {
			yourcards.set(i, new JTextField(User.MyDeck.get(i).toString(), 15));
		}

		yourmove.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			boolean finishedDrawing = true;
			boolean eightPlayed = false;
			int c = 0;

			public void actionPerformed(ActionEvent e) {
				String str = yourmove.getText();
				yourmove.setText("");
				if (User.finished == false) {
					for (int i = 0; i < User.MyDeck.size(); i++) {
						if (str.equals(User.MyDeck.get(i).toString())
								&& (str.equals("Eight of Diamonds") || str.equals("Eight of Spades")
										|| str.equals("Eight of Hearts") || str.equals("Eight of Clubs"))) {
							if (c != 0) {
								finishedDrawing = true;
								if (c == 1) {
									moves.append("You drew a card." + "\n");
								} else {
									moves.append("You drew " + c + " cards." + "\n");
								}
								c = 0;
							}
							User.give(pile, User.MyDeck.get(i));
							yourmove.setText("Choose a Suit");
							frame.remove(yourcards.get(i));
							frame.revalidate();
							yourcards.remove(i);
							for (JTextField j : yourcards) {
								j.repaint();
							}
							frame.revalidate();
							eightPlayed = true;
							if (User.MyDeck.size() == 0) {
								moves.append("You win!");
								moves.repaint();
								User.finished = true;
								yourmove.setEnabled(false);
								frame.revalidate();
							}
							if (Player2.finished == true) {
								User.finished = true;
							}
							if (User.finished == true) {
								Player1.finished = true;
							}
							break;
						} else if (eightPlayed == true && (str.equals("Clubs") || str.equals("Spades")
								|| str.equals("Diamonds") || str.equals("Hearts"))) {
							topcard.setText(str);
							moves.append("You chose " + str + "." + "\n");
							frame.revalidate();
							for (JTextField j : yourcards) {
								j.repaint();
							}
							frame.revalidate();
							eightPlayed = false;
							if (User.MyDeck.size() == 0) {
								moves.append("You win!");
								moves.repaint();
								User.finished = true;
								yourmove.setEnabled(false);
								frame.revalidate();
							}
							if (Player2.finished == true) {
								User.finished = true;
							}
							if (User.finished == true) {
								Player1.finished = true;
							}
							Player1.takeTurn(pile, stock, topcard, moves, Player2, yourmove, frame);
							Player2.takeTurn(pile, stock, topcard, moves, User, yourmove, frame);
							frame.revalidate();
							break;
						} else if (str.equals(User.MyDeck.get(i).toString())
								&& (User.MyDeck.get(i).getRank() == pile.TopCard().getRank()
										|| User.MyDeck.get(i).getSuit() == pile.TopCard().getSuit()
										|| User.MyDeck.get(i).getSuit().toString().equals(topcard.getText()))) {
							if (c != 0) {
								finishedDrawing = true;
								if (c == 1) {
									moves.append("You drew a card." + "\n");
								} else {
									moves.append("You drew " + c + " cards." + "\n");
								}
								c = 0;
							}
							moves.append("You played " + User.MyDeck.get(i) + "." + "\n");
							frame.revalidate();
							User.give(pile, User.MyDeck.get(i));
							topcard.setText(pile.TopCard().toString());
							frame.revalidate();
							frame.remove(yourcards.get(i));
							yourcards.remove(i);
							frame.revalidate();
							for (JTextField j : yourcards) {
								j.repaint();
							}
							frame.revalidate();
							if (User.MyDeck.size() == 0) {
								moves.append("You win!");
								moves.repaint();
								User.finished = true;
								yourmove.setEnabled(false);
								frame.revalidate();
							}
							if (Player2.finished == true) {
								User.finished = true;
							}
							if (User.finished == true) {
								Player1.finished = true;
							}
							Player1.takeTurn(pile, stock, topcard, moves, Player2, yourmove, frame);
							Player2.takeTurn(pile, stock, topcard, moves, User, yourmove, frame);
							frame.revalidate();
							break;
						} else if (str.equals("draw")) {
							if (stock.size() == 0) {
								for (int j = 0; j < pile.size() - 1; j++) {
									pile.give(stock, pile.get(j));
								}
								stock.shuffle();
							}
							finishedDrawing = false;
							stock.give(User, stock.get(stock.size() - 1));
							yourcards.add(new JTextField(User.MyDeck.get(User.MyDeck.size() - 1).toString(), 15));
							frame.add(yourcards.get(yourcards.size() - 1));
							for (JTextField j : yourcards) {
								j.repaint();
							}
							yourcards.get(yourcards.size() - 1).setEnabled(false);
							frame.revalidate();
							c++;
							break;
						}
					}
				}
			}
		});

		contentPane.add(TopCard);
		contentPane.add(topcard);
		contentPane.add(YourCards);
		for (int i = 0; i < yourcards.size(); i++) {
			contentPane.add(yourcards.get(i));
		}
		contentPane.add(YourMove);
		contentPane.add(yourmove);
		contentPane.add(scroll);

		TopCard.setEnabled(false);
		topcard.setEnabled(false);
		YourCards.setEnabled(false);
		for (int i = 0; i < yourcards.size(); i++) {
			yourcards.get(i).setEnabled(false);
		}
		moves.setEnabled(false);
		scroll.setEnabled(false);

		frame.setVisible(true);

		while (true) {
			// System.out.println(User.MyDeck.size()+", "+Player1.MyDeck.size()+",
			// "+Player2.MyDeck.size());
			if (yourcards.size() == 0) {
				yourcards.add(new JTextField("", 15));
			}
			layout.putConstraint(SpringLayout.WEST, TopCard, 5, SpringLayout.WEST, contentPane);

			layout.putConstraint(SpringLayout.NORTH, TopCard, 0, SpringLayout.NORTH, topcard);

			layout.putConstraint(SpringLayout.WEST, topcard, 5, SpringLayout.EAST, YourCards);

			layout.putConstraint(SpringLayout.NORTH, topcard, 5, SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, YourCards, 5, SpringLayout.WEST, contentPane);

			layout.putConstraint(SpringLayout.NORTH, YourCards, 5, SpringLayout.SOUTH, topcard);

			for (int i = 0; i < yourcards.size(); i++) {
				layout.putConstraint(SpringLayout.WEST, yourcards.get(i), 5, SpringLayout.EAST, YourCards);
			}

			layout.putConstraint(SpringLayout.NORTH, yourcards.get(0), 5, SpringLayout.SOUTH, topcard);

			for (int i = 1; i < yourcards.size(); i++) {
				layout.putConstraint(SpringLayout.NORTH, yourcards.get(i), 5, SpringLayout.SOUTH, yourcards.get(i - 1));
			}

			layout.putConstraint(SpringLayout.WEST, YourMove, 5, SpringLayout.WEST, contentPane);

			layout.putConstraint(SpringLayout.NORTH, YourMove, 5, SpringLayout.SOUTH,
					yourcards.get(yourcards.size() - 1));

			layout.putConstraint(SpringLayout.WEST, yourmove, 5, SpringLayout.EAST, YourCards);

			layout.putConstraint(SpringLayout.NORTH, yourmove, 5, SpringLayout.SOUTH,
					yourcards.get(yourcards.size() - 1));

			layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, yourmove);

			layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST, contentPane);

			layout.putConstraint(SpringLayout.EAST, yourmove, 0, SpringLayout.EAST,
					yourcards.get(yourcards.size() - 1));

			layout.putConstraint(SpringLayout.SOUTH, contentPane, 5, SpringLayout.SOUTH, scroll);

			frame.setSize(275, (yourcards.size() + 2) * 40 + scroll.getHeight() + 5);
			
			System.out.println("Jello");
		}
	}
}

