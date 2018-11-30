import java.util.ArrayList;
 
// Inherit from a class called Person.
public class Player extends Person {
	private String name;
	private int chips;
	private int bet;
	
	
	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
		bet = 0;
		
	}
	
	public String getName() {
		return name;
	}
	

	public int makeBet() {
		if (chips == 0) {
			bet = 0;
			return bet;
		}
		else {
			bet = 1;
			return bet;
		}
	}
	
	
// Need to override abstract method hitMe() as Player is inherited from Person. 
	// Change the input parameters accordingly. 
	public boolean hit_me(Table tbl) {
		int x = 0; 
		ArrayList<Card> tblCards = this.getOneRoundCard();
		for (Card c: tblCards) {
			x = x + c.getRank();
		}
		
		if (x <= 16) {
			return true;
		}
		else  {
			return false;
		}
	}
	
// Create method getCurrentChips().
	// this method returns the player's remaining number of chips.
	public int getCurrentChips() {
		return chips;
		
	}
	
// Create method increaseChips(int diff).
	// this method is void return type, it sets the number of chips for the player.
	// it increase the number of Chips for the player when it is called.
	public void increaseChips(int diff) {
		chips = chips + diff;
	}
	
// Create method sayHello().
	// this method is void, Player automatically say hello.
	// player introduce himself, name and number of chips he has.
	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	
	
}
