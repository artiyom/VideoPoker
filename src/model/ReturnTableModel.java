package model;
import java.util.ArrayList;
/**
 * This class represents the return table of the game: it says what we get for what hand
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class ReturnTableModel {
	private final ArrayList <HandModel> sampleHandNames = new ArrayList<HandModel>();
	private final int[][] returnTable;
	private final int ROWS = 9;
	private final int COLUMNS = 5;
	public ReturnTableModel() {
		/**
		 * The following code creates samples of 9 possible hands in this game
		 * These 9 hands are then used to calculate the entire return table
		 */
		
		//royal flush
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.KING), 
				new CardModel(CardSuit.CLUBS, CardRank.QUEEN), new CardModel(CardSuit.CLUBS, CardRank.JACK),
				new CardModel(CardSuit.CLUBS, CardRank.TEN), new CardModel(CardSuit.CLUBS, CardRank.ACE)}));
		
		//Straight flush
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.KING), 
				new CardModel(CardSuit.CLUBS, CardRank.QUEEN), new CardModel(CardSuit.CLUBS, CardRank.JACK),
				new CardModel(CardSuit.CLUBS, CardRank.TEN), new CardModel(CardSuit.CLUBS, CardRank.NINE)}));
		
		//four of a kind
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.ACE), 
				new CardModel(CardSuit.SPADES, CardRank.ACE), new CardModel(CardSuit.DIAMONDS, CardRank.ACE),
				new CardModel(CardSuit.CLUBS, CardRank.FIVE), new CardModel(CardSuit.HEARTS, CardRank.ACE)}));
		
		//full house
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.ACE), 
				new CardModel(CardSuit.SPADES, CardRank.ACE), new CardModel(CardSuit.DIAMONDS, CardRank.ACE),
				new CardModel(CardSuit.CLUBS, CardRank.FIVE), new CardModel(CardSuit.HEARTS, CardRank.FIVE)}));
		//flush
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.ACE), 
				new CardModel(CardSuit.CLUBS, CardRank.TEN), new CardModel(CardSuit.CLUBS, CardRank.FOUR),
				new CardModel(CardSuit.CLUBS, CardRank.FIVE), new CardModel(CardSuit.CLUBS, CardRank.NINE)}));
		
		//straight
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.ACE), 
				new CardModel(CardSuit.SPADES, CardRank.KING), new CardModel(CardSuit.DIAMONDS, CardRank.QUEEN),
				new CardModel(CardSuit.CLUBS, CardRank.JACK), new CardModel(CardSuit.HEARTS, CardRank.TEN)}));
		
		//three of a kind
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.ACE), 
				new CardModel(CardSuit.SPADES, CardRank.ACE), new CardModel(CardSuit.DIAMONDS, CardRank.ACE),
				new CardModel(CardSuit.CLUBS, CardRank.FIVE), new CardModel(CardSuit.HEARTS, CardRank.NINE)}));
		//two pairs
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.ACE), 
				new CardModel(CardSuit.SPADES, CardRank.ACE), new CardModel(CardSuit.DIAMONDS, CardRank.FOUR),
				new CardModel(CardSuit.CLUBS, CardRank.FOUR), new CardModel(CardSuit.HEARTS, CardRank.NINE)}));
		
		//pair
		sampleHandNames.add(new HandModel(new CardModel[]{new CardModel(CardSuit.CLUBS, CardRank.ACE),
				new CardModel(CardSuit.SPADES, CardRank.ACE), new CardModel(CardSuit.DIAMONDS, CardRank.FOUR),
				new CardModel(CardSuit.CLUBS, CardRank.FIVE), new CardModel(CardSuit.HEARTS, CardRank.NINE)}));
		
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
				returnTable[i][j] = sampleHandNames.get(j).getHandName().getReturnFactor() * (i+1);
							}
		}
		returnTable[4][0] = 4000;
		
		}
		/**
		 * 
		 * @param bet
		 * @param handModel
		 * @return The return factor for a given hand with a given bet
		 */
		public int getReturnFactor(int bet, HandModel handModel) {
			return returnTable[bet-1][sampleHandNames.indexOf(handModel)];
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
			return this.sampleHandNames.get(i).getHandNameStr();
		}

}
