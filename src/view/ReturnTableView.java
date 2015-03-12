package view;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.ReturnTableModel;
/**
 * This is a GUI class that prints the return table on the main window. 
 * This is the View class while the model class is the ReturnTableModel
 * @see ReturnTableModel
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class ReturnTableView extends JPanel {

	private static final long serialVersionUID = 1L;

	private BlinkingLabelView[][] infoLbls;
	
	private ReturnTableModel model = new ReturnTableModel();
	/**
	 * 6 panels are needed to display the HandModel names, and 5 return tables
	 */
	private JPanel verticalPanels[] = new JPanel[6];
	
	private int highlightedColumn;
	
	
	private final int COLUMN = 6;
	private final int ROW = 9;
	
	/**
	 * Main constructor.  
	 */
	public ReturnTableView() {
		infoLbls = new BlinkingLabelView[COLUMN][ROW];
		updateLabels();
		this.highlightedColumn = 1;
		this.highlightColumn();
	}
	
	/**
	 * Recreates labels on every call, but allows the previously highlighted column to stay highligted
	 */
	public void updateLabels() {
		this.removeAll();
		
		this.setLayout(new GridLayout(1, 6));
		this.setBorder(BorderFactory.createLineBorder(new Color(252, 216, 0)));
		
		int i, j;
		for (i = 0; i < COLUMN; i++) {
			this.verticalPanels[i] = new JPanel();
			this.add(verticalPanels[i]);
			this.verticalPanels[i].setLayout(new GridLayout(9, 1));
			this.verticalPanels[i].setBackground(new Color(0, 0, 64));
			this.verticalPanels[i].setBorder(BorderFactory.createLineBorder(new Color(252, 216, 0)));
			
			/**
			 * Obtains the table info from the model (ReturnTableModel)
			 */
			for (j = 0; j < ROW; j++) {
				infoLbls[i][j] = new BlinkingLabelView();
				if(i == 0) {
					infoLbls[i][j].setText(model.getHandName(j));
					verticalPanels[i].add(infoLbls[i][j]);
				}
				else {
					infoLbls[i][j].setText(Integer.toString(model.getCellInfo(i, j)));
					verticalPanels[i].add(infoLbls[i][j]);
				}
				
			}
		}
		verticalPanels[this.highlightedColumn].setBackground(new Color(176, 0, 0));
	}
	
	/**
	 * Makes the given row in the table to blink.
	 * @see BlinkingLabelView
	 * @param handName
	 */
	public void startBlinking(String handName) {
		int row = 0;
		switch(handName) {
			case "Jacks Or Better": row = 8; break;
			case "Two Pair": row = 7; break;
			case "Three of a Kind": row = 6; break;
			case "Straight": row = 5; break;
			case "Flush": row = 4; break;
			case "Full House": row = 3; break;
			case "Four of a Kind": row = 2; break;
			case "Straight Flush": row = 1; break;
			case "Royal Flush": row = 0; break;
		}
		infoLbls[0][row].startBlinking();
		infoLbls[this.highlightedColumn][row].startBlinking();
	}
	public void stopBlinking(String handName) {
		int row = 0;
		switch(handName) {
			case "Jacks Or Better": row = 8; break;
			case "Two Pair": row = 7; break;
			case "Three of a Kind": row = 6; break;
			case "Straight": row = 5; break;
			case "Flush": row = 4; break;
			case "Full House": row = 3; break;
			case "Four of a Kind": row = 2; break;
			case "Straight Flush": row = 1; break;
			case "Royal Flush": row = 0; break;
		}
		infoLbls[0][row].stopBlinking();
		infoLbls[this.highlightedColumn][row].stopBlinking();
	}
	
	/**
	 * Highlights a column with red color
	 */
	private void highlightColumn(){
		updateLabels();
		verticalPanels[this.highlightedColumn].setBackground(new Color(176, 0, 0));

	}
	
	/**
	 * These 2 methods use the previous one to highlight a column
	 * They are used in the ReturnTableController
	 */
	public void higherBet() {
		if(this.highlightedColumn < 5) {
			this.highlightedColumn++;
		}
		this.highlightColumn();
		
	}
	
	public void lowerBet() {
		if(this.highlightedColumn >= 1) {
			this.highlightedColumn--;
		}
		this.highlightColumn();
	}
	
	public int getFactor() {
		return this.highlightedColumn;
	}

}
