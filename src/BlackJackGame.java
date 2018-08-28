

import java.util.ArrayList;

public class BlackJackGame {
	private Deck<BlackJackCard> deck;
	private BlackJackHand[] hands;
	private static final int HIT_UNTIL = 17;
	
	public BlackJackGame(int numPlayers) {
		hands = new BlackJackHand[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			hands[i] = new BlackJackHand();
		}
	}

	public boolean bustOr21(int player) {
		if(hands[player].busted() || hands[player].is21()) {
			return false;
		}
		return true;
	}

	public void playerAddCard(int player) {
		BlackJackHand hand = hands[player];
		hand.addCard(deck.dealCard());
	}
	
	public boolean dealInitial() {
		for (BlackJackHand hand : hands) {
			BlackJackCard card1 = deck.dealCard();
			BlackJackCard card2 = deck.dealCard();
			if (card1 == null || card2 == null) {
				return false;
			}
			hand.addCard(card1);
			hand.addCard(card2);	
		}
		return true;
	}
	
	public ArrayList<Integer> getBlackJacks() {
		ArrayList<Integer> winners = new ArrayList<Integer>();
		for (int i = 0; i < hands.length; i++) {
			if (hands[i].isBlackJack()) {
				winners.add(i);
			}
		}
		return winners;
	}
	
	//public boolean playHand(int i) {
	//	BlackJackHand hand = hands[i];
	//	return playHand(hand);
	//}
	
	public boolean playHand(BlackJackHand hand) {
		while (hand.score() < HIT_UNTIL) {
			BlackJackCard card = deck.dealCard();
			if (card == null) {
				return false;
			}
			hand.addCard(card);
		}
		return true;
	}

	public void setUpHand(BlackJackHand hand) {
		BlackJackCard card1 = deck.dealCard();
		BlackJackCard card2 = deck.dealCard();

	}


	public void setupAllHands() {
		for (BlackJackHand hand : hands) {
			setUpHand(hand);
		}
	}
	
	public boolean playAllHands(){
		for (BlackJackHand hand : hands) {
			if (!playHand(hand)) {
				return false;
			}
		}
		return true;
	}
	
	public void getResults() {
		ArrayList<Object> winners = new ArrayList<>();
		ArrayList<Object> losers = new ArrayList<>();
		ArrayList<Object> push = new ArrayList<>();
		BlackJackHand dealer = hands[hands.length - 1];
		int dealerScore = dealer.score();
		System.out.println(dealerScore);
		for (int i = 0; i < hands.length - 1; i++) {
			BlackJackHand hand = hands[i];
			if(!dealer.busted()) {
				if (hand.busted() || hand.score() < dealerScore) {
					losers.add(i + 1);
				} else if(hand.score() > dealerScore) {
					winners.add(i + 1);
				} else {
					push.add(i + 1);
				}
			} else {
				if(hand.busted()) {
					losers.add(i + 1);
				} else if(hand.score() < 21) {
					winners.add(i + 1);
				}
			}

			if(winners.size() == 0 || push.size() == 0) {
				winners.add("Dealer");
			}
		}
		if (winners.size() > 0) {
			System.out.print("Winners: ");
			for (Object i : winners) {
				if(i == winners.get(winners.size() - 1)) {
					System.out.print(i);
				} else {
					System.out.print(i + ", ");
				}
			}
		}
		if (push.size() > 0) {
			System.out.print("Push: ");
			for (Object i : push) {
				if(i == push.get(push.size() - 1)) {
					System.out.print(i);
				} else {
					System.out.print(i + ", ");
				}
			}
		}
		if (losers.size() > 0) {
			System.out.print("Losers: ");
			for (Object i : losers) {
				if(i == losers.get(losers.size() - 1)) {
					System.out.print(i);
				} else {
					System.out.print(i + ", ");
				}
			}
		}
	}
	
	public void initializeDeck() {
		ArrayList<BlackJackCard> cards = new ArrayList<>();
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j <= 3; j++) {
				Suit suit = Suit.getSuitFromValue(j);
				BlackJackCard card = new BlackJackCard(i, suit);
				cards.add(card);
			}
		}
		
		deck = new Deck<BlackJackCard>();
		deck.setDeckOfCards(cards);
		deck.shuffle();	
	}
	
	public void printHandsAndScore() {
		for (int i = 0; i < hands.length; i++) {
			if(i == hands.length - 1) {
				System.out.print("Dealer" + " (" + hands[i].score(0) + "): ");
				hands[i].print(0);
				System.out.println("");
			} else {
				System.out.print("Hand " + (i + 1) + " (" + hands[i].score() + "): ");
				hands[i].print();
				if(hands[i].busted()) {
					System.out.print("bust");
				}
				System.out.println("");
			}
		}
	}
	public void printFinalHandsAndScore() {
		for (int i = 0; i < hands.length; i++) {
			if(i == hands.length - 1) {
				System.out.print("Dealer" + " (" + hands[i].score() + "): ");
				hands[i].print();
				System.out.println("");
			} else {
				System.out.print("Hand " + (i + 1) + " (" + hands[i].score() + "): ");
				hands[i].print();
				if(hands[i].busted()) {
					System.out.print("bust");
				}
				System.out.println("");
			}
		}
	}
}
