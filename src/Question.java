
import java.util.ArrayList;
import java.util.Scanner;

public class Question {

	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of players: ");
		int numPlayers = input.nextInt();
		numPlayers++;
		
		BlackJackGame blackJackGame = new BlackJackGame(numPlayers);
		blackJackGame.initializeDeck();
		boolean success = blackJackGame.dealInitial();
			if (!success) {
				System.out.println("Error. Out of cards.");
			} else {
			System.out.println("-- Initial --");
			blackJackGame.printHandsAndScore();
			ArrayList<Integer> blackjacks = blackJackGame.getBlackJacks();
			if (blackjacks.size() > 0) {
				System.out.print("Blackjack at ");
				for (int i : blackjacks) {
					if(i == blackjacks.get(blackjacks.size() - 1)) {
						System.out.print(i + 1);
					} else {
						System.out.print((i + 1) + ", ");
					}
				}
				System.out.println("");
			}
				for (int i = 0; i < numPlayers - 1; i++) {
					int temp = 0;
					while(temp != 2) {
						System.out.println("Player " + (i + 1));
						System.out.println("Enter 1 to hit or 2 to stay");
						System.out.println("1) hit");
						System.out.println("2) stay");
						temp = input.nextInt();
						if(temp == 1) {
							blackJackGame.playerAddCard(i);
							blackJackGame.printHandsAndScore();
							if(blackJackGame.bustOr21(i)) {
								continue;
							} else {
								temp = 2;
								continue;
							}
						} else if(temp == 2) {
							break;
						} else {
							System.out.println("Invalid answer");
						}
					}
				}
				//blackJackGame.printHandsAndScore();
				//blackJackGame.getResults();
				success = blackJackGame.playDealerHand();
				blackJackGame.printFinalHandsAndScore();
				blackJackGame.getResults();
				/*if (!success) {
					System.out.println("Error. Out of cards.");
				} else {
					System.out.println("\n-- Completed Game --");
					blackJackGame.printFinalHandsAndScore();
					blackJackGame.getResults();
				}*/
		}
	}

}
