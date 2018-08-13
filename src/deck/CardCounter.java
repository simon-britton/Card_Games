package deck;
import java.util.Observable;
import java.util.Observer;
import deck.Deck;

public class CardCounter implements Observer{
	private Deck deck;
	public CardCounter(){
		
	}
	@Override
	public void update(Observable observable, Object arg1) {
		deck = (Deck) observable;
		System.out.println(deck.size());
	}
}
