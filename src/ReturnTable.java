import java.util.ArrayList;
/**
 * This class represents the return table of the game: it says what we get for what hand
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class ReturnTable {
	private final ArrayList <Hand> handNames = new ArrayList<Hand>();
	private final int[][] returnTable;
	private final int ROWS = 9;
	private final int COLUMNS = 5;
	public ReturnTable() {
		/**
		 * The following code creates samples of 9 possible hands in this game
		 * These 9 hands are then used to calculate the entire return table
		 */
		
		//royal flush
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.KING), 
				new Card(Card.CLUBS, Card.QUEEN), new Card(Card.CLUBS, Card.JACK),
				new Card(Card.CLUBS, Card.TEN), new Card(Card.CLUBS, Card.ACE)}));
		
		//Straight flush
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.KING), 
				new Card(Card.CLUBS, Card.QUEEN), new Card(Card.CLUBS, Card.JACK),
				new Card(Card.CLUBS, Card.TEN), new Card(Card.CLUBS, Card.NINE)}));
		
		//four of a kind
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.ACE), 
				new Card(Card.SPADES, Card.ACE), new Card(Card.DIAMONDS, Card.ACE),
				new Card(Card.CLUBS, Card.FIVE), new Card(Card.HEARTS, Card.ACE)}));
		
		//full house
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.ACE), 
				new Card(Card.SPADES, Card.ACE), new Card(Card.DIAMONDS, Card.ACE),
				new Card(Card.CLUBS, Card.FIVE), new Card(Card.HEARTS, Card.FIVE)}));
		//flush
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.ACE), 
				new Card(Card.CLUBS, Card.TEN), new Card(Card.CLUBS, Card.FOUR),
				new Card(Card.CLUBS, Card.FIVE), new Card(Card.CLUBS, Card.NINE)}));
		
		//straight
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.ACE), 
				new Card(Card.SPADES, Card.KING), new Card(Card.DIAMONDS, Card.QUEEN),
				new Card(Card.CLUBS, Card.JACK), new Card(Card.HEARTS, Card.TEN)}));
		
		//three of a kind
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.ACE), 
				new Card(Card.SPADES, Card.ACE), new Card(Card.DIAMONDS, Card.ACE),
				new Card(Card.CLUBS, Card.FIVE), new Card(Card.HEARTS, Card.NINE)}));
		//two pairs
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.ACE), 
				new Card(Card.SPADES, Card.ACE), new Card(Card.DIAMONDS, Card.FOUR),
				new Card(Card.CLUBS, Card.FOUR), new Card(Card.HEARTS, Card.NINE)}));
		
		//pair
		handNames.add(new Hand(new Card[]{new Card(Card.CLUBS, Card.ACE),
				new Card(Card.SPADES, Card.ACE), new Card(Card.DIAMONDS, Card.FOUR),
				new Card(Card.CLUBS, Card.FIVE), new Card(Card.HEARTS, Card.NINE)}));
		
		/**
		 * Calculation of the return table
		 * The return for the every next column is twice of that of current column
		 * e.g.
		 * Hand3 3  6  9  12 15
		 * Hand2 2  4  6  8  10
		 * Hand1 1  2  3  4  5
		 */
		returnTable = new int[COLUMNS][ROWS];
		int i, j;
		for (i = 0; i < COLUMNS; i++) {
			for (j = 0; j < ROWS; j++) {
				returnTable[i][j] = handNames.get(j).getReturnFactor() * (i+1);
							}
		}
		returnTable[4][0] = 4000;
		
		}
		/**
		 * 
		 * @param bet
		 * @param hand
		 * @return The return factor for a given hand with a given bet
		 */
		public int getReturnFactor(int bet, Hand hand) {
			return returnTable[bet-1][handNames.indexOf(hand)];
		}
		/**
		 * Used by ReturnTableView to obtain information for it's every label to display.
		 * @param i
		 * @param j
		 * @return
		 */
		public int getCellInfo(int i, int j) {
			return returnTable[i-1][j];
		}
		
		public String getHandName(int i) {
			return this.handNames.get(i).getHandName();
		}

}
