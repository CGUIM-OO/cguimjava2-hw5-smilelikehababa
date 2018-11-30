import java.util.ArrayList;
import java.util.Random;

//Add new Field into Deck class: openCard
	// openCard is an ArrayList<Card> type, which keeps a record of all the cards that is opened on the table.
public class Deck{
	private ArrayList<Card> cards;
	public ArrayList<Card> usedCard;
	private ArrayList<Card> openCard;
	public int nUsed;
	
//Edit Current Method: Shuffle().
	//Remember to reset openCard because all the cards are collected back to shuffle, hence no more cards left opened on the table.
	public void shuffle() {
		if (nUsed > 0) {
			for (int i = usedCard.size() -1 ; i >= 0; i--) {
				Card onHand = usedCard.get(i);
				cards.add(onHand);
				nUsed --;
			}
		}
		usedCard.clear();
		openCard.clear();
		
		for (int i = cards.size()-1; i>=0; i--) {
			Card onHand = cards.get(i);
			Random rnd = new Random(); 
			int j = rnd.nextInt(i+1);
			Card onHand2 = cards.get(j);
			cards.set(j,onHand);
			cards.set(i,onHand2);
		}

	}
	
//Edit Current Method: getOneCard().
	// Add in an argument: boolean isOpened 
	// getOneCard() is a method that gives player a card, and return the card object.
	// getOneCard(boolean isOpened) method decides if the card is open. 
	// if the card is open, then add into the array list openCard. 
	public Card getOneCard(boolean isOpened) {
		
		if (cards.size() <= 0) {
			shuffle();
		}
		Card x = cards.get(0);
		usedCard.add(x);
		cards.remove(0);
		if (isOpened == true) {
			openCard.add(x);
		}
		nUsed ++;
		return x;
			
	}
	
//Add openCard in the constructor.
	
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		usedCard =new ArrayList<Card>();
		nUsed = 0;
		openCard = new ArrayList<Card>();
		for(Card.Suit s: Card.Suit.values()) {
			for(int j=1; j<=13; j++) {
				Card c = new Card(s,j);
				cards.add(c);
			}
		}
		shuffle();
		
	}	
	public void printDeck() {
		for(Card c: cards) {
			c.printCard();
		}
	}
	
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	
	public ArrayList<Card>getOpenedCard(){
		return openCard; 
	}
}