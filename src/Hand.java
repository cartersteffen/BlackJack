import java.util.ArrayList;

public class Hand <T extends Card> {
	protected ArrayList<T> cards = new ArrayList<>();
	
	public int score() {
		int score = 0;
		for (T card : cards) {
			score += card.value();
		}
		return score;
	}
	
	public int score(int index) {
		int score = 0;
		score = cards.get(index).value();
		return score;
	}	
	
	public void addCard(T card) {
		cards.add(card);
	}	
	
	public void print() {
		for (Card card : cards) {
			card.print();
		}
	}	
	public void print(int index) {
		Card card = cards.get(index);
		card.print();
	}	
}
