package model;
import java.util.ArrayList;
import java.util.Random;
/**
 * This Class represents a CardModel Deck. It has an array of 52 cards.
 * The cards are being created, shuffled here.
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class CardDeckModel {
	private ArrayList<CardModel> cardDeck = new ArrayList<CardModel>();
	private int cardsLeft;
	
	public CardDeckModel() {
		renewDeck();
	}
	
	
	public void renewDeck() {
		initializeDeck();
		cardsLeft = cardDeck.size();
		shuffle();
	}
	
	//This method initializes the array by creating 52 cards. They are Not shuffled here.
	private void initializeDeck() {
		for (CardSuit suit : CardSuit.values()) {
			for (CardRank rank : CardRank.values()) {
				cardDeck.add(new CardModel(suit, rank));
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
		ArrayList<CardModel> temporaryArray = new ArrayList<CardModel>(cardDeck);
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
	public CardModel getCard() {
		CardModel cardModel = new CardModel();
		if (cardsLeft > 0) {
			cardModel = cardDeck.get(cardsLeft-1);
			cardDeck.remove(cardsLeft-1);
			cardsLeft--;
		}
		return cardModel;
	}
	

}
