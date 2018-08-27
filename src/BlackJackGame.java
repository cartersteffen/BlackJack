

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
	
	public ArrayList<Object> getWinners() {
		ArrayList<Object> winners = new ArrayList<Object>();
		int winningScore = 0;
		for (int i = 0; i < hands.length; i++) {
			BlackJackHand hand = hands[i];
			if (!hand.busted()) {
				if (hand.score() > winningScore) {
					winningScore = hand.score();
					winners.clear();
					if(i == hands.length - 1) {
						winners.add("Dealer");
					} else {
						winners.add(i + 1);
					}
				} else if (hand.score() == winningScore) {
					if(i == hands.length - 1) {
						winners.add("Dealer");
					} else {
						winners.add(i + 1);
					}
				}
			}
		}
		return winners;
	}
	
	public void initializeDeck() {
		ArrayList<BlackJackCard> cards = new ArrayList<BlackJackCard>();
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
				System.out.println("");
			}
		}
	}
}