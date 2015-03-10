import java.util.ArrayList;
import java.util.Random;
/**
 * This Class represents a Card Deck. It has an array of 52 cards.
 * The cards are being created, shuffled here.
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class CardDeck {
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	private int cardsLeft;
	
	public CardDeck() {
		renewDeck();
	}
	
	
	public void renewDeck() {
		initializeDeck();
		cardsLeft = cardDeck.size();
		shuffle();
	}
	
	//This method initializes the array by creating 52 cards. They are Not shuffled here.
	private void initializeDeck() {
		int i, j;
		for (i = Card.SPADES; i <= Card.CLUBS; i++) {
			for (j = Card.TWO; j <= Card.ACE; j++) {
				cardDeck.add(new Card(i, j));
			}
			
		}
	}
	
	
	//Used in debugging purposes.
	public void printDeck() {
		System.out.println("Cards left: " + cardDeck.size());
		int i;
		for (i = 0; i < cardDeck.size(); i++) {
			System.out.println(cardDeck.get(i).getCardName());
		}
	}
	
	/**
	 * The following method shuffles the deck using Fisher–Yates shuffle algorithm.
	 * We create new temporary array, clear the old one. Take a random number between 0 and the array's size
	 * Then copy that element to the new array, and remove it from the temporary one. Temporary array
	 * gets smaller in size by 1
	 */
	public void shuffle() {
		Random rand = new Random();
		ArrayList<Card> temporaryArray = new ArrayList<Card>(cardDeck);
		cardDeck.clear();
		
	    int i = 0;
	    while (i < 52) {
	    	int random = rand.nextInt(temporaryArray.size());
	    	cardDeck.add(temporaryArray.get(random));
	    	temporaryArray.remove(random);
	        i++;
	        }
	}
	
	/**
	 * Returns the 'top' card of the Deck. 
	 * @return
	 */
	public Card getCard() {
		Card card = new Card();
		if (cardsLeft > 0) {
			card = cardDeck.get(cardsLeft-1);
			cardDeck.remove(cardsLeft-1);
			cardsLeft--;
		}
		return card;
	}
	

}
