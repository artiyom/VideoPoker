package view;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import viewController.CardViewController;
import model.PlayerCardsModel;

/**
 * This is a graphical (view) class for the PlayerCardsModel (model) class
 * @see PlayerCardsModel
 * @author Artyom M. a.k.a. artiyom
 *
 */
public class PlayerCardsView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private CardViewController[] card = new CardViewController[5];
	private PlayerCardsModel model = new PlayerCardsModel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
	
	public PlayerCardsView(PlayerCardsModel model) {
		this.leftPanel.setBackground(new Color(0, 0, 100));
		this.rightPanel.setBackground(new Color(0, 0, 100));
		this.model = model;
		this.setLayout(new GridLayout(1, 7));
		this.setBackground(new Color(0, 0, 100));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		int i = 0;
		this.add(leftPanel);
		/**
		 * this cycle adds just 5 dummy cards (face down) to the panel. Not playable
		 */
		while (i < 5) {
			this.add(new CardViewController());
			i++;
		}
		this.add(rightPanel);
		
	}
	
	/**
	 * This method updates the panel and every time obtains the newest version of PlayerCardsModel's cards
	 */
	public void updateCards() {
		this.removeAll();
		int i = 0;
		this.add(leftPanel);
		while (i < 5) {
			card[i] = new CardViewController(model.getCard(i));
			this.add(card[i]);
			i++;
		}
		this.add(rightPanel);
	}
}
