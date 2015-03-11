package viewController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ReturnTableController;
import view.PlayerCardsView;
import view.ReturnTableView;
import view.WagerCreditView;
import model.CreditModel;
import model.PlayerCardsModel;
import model.ReturnTableModel;
import model.WagerModel;
/**
 * This is a GUI class that contains the Deal/Draw button
 * Also it has most of the game logic
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class DealButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	
	private JButton dealBtn = new JButton("Deal");
	
	/**
	 * This class receives references to almost all other game objects
	 * @param model
	 * @param cardPanel
	 * @param returnPanelView
	 * @param creditModel
	 * @param wagerCreditView
	 * @param wagerModel
	 * @param betFactor
	 */
	public DealButtonPanel(PlayerCardsModel model, PlayerCardsView cardPanel, ReturnTableView returnPanelView, 
			CreditModel creditModel, WagerCreditView wagerCreditView, WagerModel wagerModel, ReturnTableController betFactor) {

		this.add(dealBtn);
		this.setBackground(new Color(0, 0, 100));
		
		/**
		 * Once the Deal button is pressed, it becomes Draw button and the other way around
		 * 
		 */
		dealBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * If the button was Deal.
				 * It calls the deal() method in the PlayerCardsModel object (model)
				 * Then it withdraws money from the credit (if there is money there)
				 * Disables all other Buttons in the game, so nothing can be changed while player is
				 * in game.
				 * Checks if there is a hand and makes the sufficient hand blink on the returnPanelView
				 * Then all other GUI elements are updated so they show the latest states.
				 */
				if(dealBtn.getText().equals("Deal")) {
					try {
						
						creditModel.withdrawMoney(wagerModel.getWagerSize() * returnPanelView.getFactor());
					}
					catch(IllegalStateException e1) {
						JOptionPane.showMessageDialog(null, "Your don't have enogh credit "
								+ "to make this bet. ");
						return;
					}
					
					model.deal();
					returnPanelView.updateLabels();
					
					
					wagerCreditView.disableButtons();
					betFactor.disableButtons();
					if (model.isTherePokerHand() == true) {
						returnPanelView.startBlinking(model.getHandName());
					}
					cardPanel.updateCards();
					wagerCreditView.updateLabeles();
					
					
					dealBtn.setText("Draw");
					
				}
				/**
				 * This part works when the button was the Draw.
				 * It calls model's (PlayerCardsModel) draw() method and then updates Graphical cards
				 * Checks if there is a hand and adds sufficient amount of money -
				 * (bet * return factor * bet factor) to the CreditModel. Enables all the disabled buttons. 
				 * Changes the Draw button back to Deal.
				 */
				else {
					returnPanelView.stopBlinking(model.getHandName());
					model.draw();
					cardPanel.updateCards();
					returnPanelView.updateLabels();
					if (model.isTherePokerHand() == true) {
						returnPanelView.startBlinking(model.getHandName());
						creditModel.addMoney(wagerModel.getWagerSize() * (new ReturnTableModel()).getReturnFactor(returnPanelView.getFactor(), model.getHand()));
						wagerCreditView.updateLabeles();
					}
					
					
					wagerCreditView.enableButtons();
					betFactor.enableButtons();
					dealBtn.setText("Deal");
					
				}
				
			}
			
			
		});
	}

}
