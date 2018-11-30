import java.util.ArrayList;

public class Table {

// Create new Class field, MAXPLAYER = 4, integer type, final. 
	// This field states the maximum number of players at a table. 
	public static final int MAXPLAYER = 4;
	
// Create instance field which its data type is Deck - to store all the cards.
// Create instance field which its data type is Player[] - to store all the players.
// Create instance field which its data type is Dealer - to store one Dealer.
// Create instance field int[] pos_betArray - to store all the bets from all players. 
	private Deck tableCards;
	private Player[] currentPlayers;
	private Dealer currentDealer;
	private int[] pos_betArray;

// Create Constructor for Table, with input variable int nDeck.
	// Instantiate Deck class, using the Deck instance field created above.
	// Create and initialize above Player's instance field created above.
	// Declare a Player array's length for MAXPLAYER.
	public Table(int nDeck) {
		tableCards = new Deck(nDeck);
		currentPlayers = new Player[MAXPLAYER];
		pos_betArray = new int[MAXPLAYER];
		
		
	}
	
// Create a method called set_player
	// Its input is pos (the position of the player at the table) and p (the Player instance) 
	// This is a setter method.
	// It sets the Player to the position at the table.
	// as pos will be counting from 1 to 4 and not 0, we must take into account the difference of 1 for list index. 
	public void set_player(int pos, Player p) {
		if (pos < 4 ) {
			currentPlayers[pos] = p;
		}
		else {
			System.out.println("position is invalid, Player cannot join the table.");
		}
	}
	
// Create a method called get_player, which return all the players on the table.
	// This is a getter method.
	// Return type is Player[].
	public Player[] get_player() {
		return currentPlayers;
	}
	
// Create a method called set_dealer.
	// Input: d (an instance of Dealer) 
	// this is to allocate a Dealer to this table.
	public void set_dealer (Dealer d) {
		currentDealer = d;
	}

// Create a method called get_face_up_card_of_dealer.
	// This returns the dealer's faced up card, which is the second card distributed to the dealer. 
	public Card get_face_up_card_of_dealer() {
		ArrayList<Card> cardsOfDealer = currentDealer.getOneRoundCard();
		return cardsOfDealer.get(1);
		
	}
	
// Create a method called ask_each_player_about_bets.
	// This will ask every player to say hello (using say_hello())
	// Then they will be asked to make bet (using make_bet())
	// After player make a bet, store the bet in pos_betArray for later use.
	private void ask_each_player_about_bets() {
		int i = 0 ;
		for (Player x: currentPlayers) {
			x.sayHello();
			int xBet = x.makeBet();
			pos_betArray[i] = xBet;
			i++;
		}
	}

// Create method called distribute_cards_to_dealer_and_players().
	// This method distribute cards to both the players and the dealer. 
	// This is the order of distribution: 
		// two cards opened to players.
		// one closed card and one opened card to Dealer (Use setOneRoundCard())
		// After Dealer, print Dealer's card that is faced up (Use printCard())

	private void distribute_cards_to_dealer_and_players() {
		tableCards.shuffle();
		for (Player x: currentPlayers) {
			Card firstCard = tableCards.getOneCard(true);
			Card secondCard = tableCards.getOneCard(true);
			ArrayList<Card> cards = new ArrayList<Card>();
			cards.add(firstCard);
			cards.add(secondCard);
			x.setOneRoundCard(cards);
		}
		Card dFirstCard = tableCards.getOneCard(false);
		Card dSecondCard = tableCards.getOneCard(true);
		ArrayList<Card> dCards = new ArrayList<Card>();
		dCards.add(dFirstCard);
		dCards.add(dSecondCard);
		currentDealer.setOneRoundCard(dCards);
		System.out.print("Dealer's face up card is " );
		dSecondCard.printCard();
		
	}
	
// Create method called ask_each_player_about_hits().
	// This method ask every Player if they want to draw a card (Use hit_me(Table tbl)).
	// According to HW4.java, use do while loop to ask all the Players.
	// Ask in order of the player's position at the table. 
	// If Player wants to hit, please print "Hit! " + Player's name + "'s cards now: ".
	// Then, print out all the cards of the Player. 
	// Lastly, Use setOneRoundCard() to reset the Player's cards in total. 
	// If Player's cards' total value is above 21 points, do not ask if the Player wants to hit.
	// If Player doesn't want to hit anymore, please print on the screen Player's name + "Pass hit!".
	// Testing if works
	private void ask_each_player_about_hits() {
		for (Player p : currentPlayers) {
			ArrayList<Card> pCard = p.getOneRoundCard();
			//Print Player's Card first.
			System.out.print(p.getName() + "'s Cards now: ");
			for (Card c:pCard) {
				c.printCard();
			}
			boolean hit = false;
			do { 
				hit = p.hit_me(this);
				
				
				
				if (hit) {
					pCard.add(tableCards.getOneCard(true));
					p.setOneRoundCard(pCard);
					System.out.print("Hit! " + p.getName() + "'s Cards now: ");
					for (Card c: pCard) {
						c.printCard();
					}
					
				}
				else {
					System.out.println(p.getName() + " Pass hit!");
					System.out.println(p.getName() + "'s hit is over!");
				}
			
			} while(hit);
		}
		}
// Create a method called ask_dealer_about_hits()
	// Ask if dealer wants to draw cards, thereafter, print out "Dealer's hit is over!"
	private void ask_dealer_about_hits() {
		
		boolean hit=false;
		do {
			hit = currentDealer.hit_me(this);
			ArrayList<Card> dCard = currentDealer.getOneRoundCard();
			
			if (hit) {
				dCard.add(tableCards.getOneCard(true));
				//currentDealer.setOneRoundCard(dCard);
				//System.out.println("Hit! Dealer's cards now: ");
				//for (Card c: dCard) {
				//	c.printCard();
				//}
			}
			else {
				System.out.println("Dealer's hit is over!");
			}
		} while(hit);
	}

// Create a method called calculate_chips()
	// This method prints out Dealer's total value and Cards. (Using printAllCard() method) 
	// "Dealer's card value is " + total value + " , Cards:" + Cards.
	// Dealer's total value must be compared with every player's total value.
	// For every player, first print out Player's name + " card value is " + Player's total value.
	// Then, compare.
		//If Dealer wins, take Player's bet and print out ", Loss" + bet + " Chips, the Chips now is: " + current chips left. (Use get_current_chips()).
		//If Dealer loses, Player win the bet and print out ", Get" + bet + " Chips, the Chips now is: " + current chips left. (Use get_current_chips()).
		//If draw, print out ", chips have no change! The Chips now is: " + current chips (Use get_current_chips()).
	private void calculate_chips() {
		int dTotalValue = currentDealer.getTotalValue();
		System.out.println("Dealer's card value is " + dTotalValue + " , Cards: " );
		currentDealer.printAllCard();
		
		
		int count = 0;
		for (Player p: currentPlayers) {
			int pTotalValue = p.getTotalValue();
			int bet = pos_betArray[count];
			String pName = p.getName();
			
			System.out.println(pName + "'s Cards: "); 
			p.printAllCard();
			
			
			if ((dTotalValue <=21 && dTotalValue > pTotalValue)||(dTotalValue <=21 && pTotalValue>21)) {
				int left = p.getCurrentChips() - bet; 
				System.out.println(pName + " card value is " + pTotalValue + ", Loss " + bet + " Chips, the Chips now is: " + left);
			
			}
			else if ((pTotalValue<=21 && dTotalValue < pTotalValue)||(dTotalValue > 21 && pTotalValue <=21) ){
				int left = p.getCurrentChips() + bet;
				System.out.println(pName + " card value is " + pTotalValue + ", Get" + bet + " Chips, the Chips now is: " + left);
				
			}
	
			
			else {
				int left = p.getCurrentChips();
				System.out.println(pName + " card value is " + pTotalValue + ", Chips have no change! The Chips now is: " + left);
			
			}
			count ++ ;
	
		}
	}

	
// Create a method called get_players_bet().
	// return type is int[]
	public int[] get_players_bet() {
		return pos_betArray;
	}
	
// Create a method play(). 
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	
	}
	
	
	
	
	
	
	
	
}
