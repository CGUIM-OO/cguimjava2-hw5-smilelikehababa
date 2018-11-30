
public class Card{
	public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}; //Suit: Clubs, Diamonds, Hearts, Spades 
	private int rank;//1~13
	
	private Suit suit; 
	
	/**
	 * @param s suit
	 * @param r rank
	 */
	public Card(Suit s ,int r){
		rank=r;
		this.suit = s;
		
	}	
	public void printCard(){
		String strSuit = "";
		String strRank = "";
		switch(this.suit) {
		case SPADES:
			strSuit = "Spades";
			break;
		case DIAMONDS:
			strSuit = "Diamonds";
			break;
		case HEARTS:
			strSuit = "Hearts";
			break;
		case CLUBS:
			strSuit = "Clubs";
			break;
		
		}
		switch(this.rank) {
		case 1:
			strRank = "Ace";
			break;
		case 13: 
			strRank = "King";
			break;
		case 12:
			strRank = "Queen";
			break;
		case 11:
			strRank = "Jack";
			break;
		default: 
			strRank = "" + this.rank;
			break;
			
		}
		System.out.println(strSuit + "," + strRank);
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace

	}
	public Suit getSuit(){
		return this.suit;
	}
	public int getRank(){
		return rank;
	}
}