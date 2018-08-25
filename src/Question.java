//package Q7_01_Deck_of_Cards;

import java.util.ArrayList;
import java.util.Scanner;

public class Question {

	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of players: ");
		int numPlayers = input.nextInt();
		numPlayers++;
		
		BlackJackGameAutomator automator = new BlackJackGameAutomator(numPlayers);
		automator.initializeDeck();
		boolean success = automator.dealInitial();
			if (!success) {
				System.out.println("Error. Out of cards.");
			} else {
			System.out.println("-- Initial --");
			automator.printHandsAndScore();
			ArrayList<Integer> blackjacks = automator.getBlackJacks();
			if (blackjacks.size() > 0) {
				System.out.print("Blackjack at ");
				for (int i : blackjacks) {
					if(i == blackjacks.get(blackjacks.size() - 1)) {
						System.out.print(i);
					} else {
						System.out.print(i + ", ");
					}
				}
				System.out.println("");
			} 
			else {
				System.out.println("Enter 1 to hit or 2 to stay");
				System.out.println("1) hit");
				System.out.println("2) stay");
				int temp = input.nextInt();
				if(temp == 1) {
					
				} else if(temp == 2) {
					
				} else {
					System.out.println("Invalid answer");
				}
				success = automator.playAllHands();
				if (!success) {
					System.out.println("Error. Out of cards.");
				} else {
					System.out.println("\n-- Completed Game --");
					automator.printFinalHandsAndScore();
					ArrayList<Object> winners = automator.getWinners();
					if (winners.size() > 0) {
						System.out.print("Winners: ");
						for (Object i : winners) {
							if(i == winners.get(winners.size() - 1)) {
								System.out.print(i);
							} else {
								System.out.print(i + ", ");
							}
						}
						//System.out.println("");
					} else {
						System.out.println("Draw. All players have busted.");
					}
				}
			}
		}
	}

}
