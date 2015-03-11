package viewController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import view.WagerCreditView;
import model.CreditModel;
import model.WagerModel;
/**
 * This is a Dialog that openes when user needs to change his bet size and the money he has
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class WagerCreditSettingWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private CreditModel creditModel;
	private WagerModel wagerModel;
	private JLabel wagerLbl;
	private JLabel moneyLeft;
	
	/**
	 * We are receiving 4 variables from the WagerCreditView class, so they can be changed here.
	 * @see WagerCreditView
	 * @param creditModel
	 * @param wagerModel
	 * @param wagerLbl
	 * @param moneyLeft
	 */
	public WagerCreditSettingWindow(CreditModel creditModel, WagerModel wagerModel, JLabel wagerLbl, JLabel moneyLeft) {
		this.creditModel = creditModel;
		this.wagerModel = wagerModel;
		this.wagerLbl = wagerLbl;
		this.moneyLeft = moneyLeft;
		
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createTitledBorder("Choose Bet Amount"));
		bottomPanel.setBorder(BorderFactory.createTitledBorder("Choose CreditModel Amount"));
		/**
		 * 2 panels that hold 4 Radio Buttons each. 
		 * Top one for bet and the buttom one for money amount.
		 */
		ButtonGroup group = new ButtonGroup();
		JRadioButton[] betPick = new JRadioButton[4];
		betPick[0] = new JRadioButton("$1");
		betPick[0].setActionCommand("1.0");
		betPick[0].addActionListener(new BetSizeListener());
		
		betPick[1] = new JRadioButton("$2");
		betPick[1].setActionCommand("2.0");
		betPick[1].addActionListener(new BetSizeListener());
		
		betPick[2] = new JRadioButton("$5");
		betPick[2].setActionCommand("5.0");
		betPick[2].addActionListener(new BetSizeListener());
		
		betPick[3] = new JRadioButton("$10");
		betPick[3].setActionCommand("10.0");
		betPick[3].addActionListener(new BetSizeListener());
		/**
		 * the flowing code obtains the atual bet-size from the WagerModel object, and sets that 
		 * bet as the selected bet among these 4 radio buttons.
		 */
		int i = 0;
		while(i < 4) {
			if(betPick[i].getActionCommand().equals(String.valueOf(wagerModel.getWagerSize()))) {
				
				betPick[i].setSelected(true);
			}
			i++;
		}
		
		
		group.add(betPick[0]);
		group.add(betPick[1]);
		group.add(betPick[2]);
		group.add(betPick[3]);
		topPanel.add(betPick[0]);
		topPanel.add(betPick[1]);
		topPanel.add(betPick[2]);
		topPanel.add(betPick[3]);
		
		ButtonGroup group2 = new ButtonGroup();
		JRadioButton[] creditPick = new JRadioButton[4];
		creditPick[0] = new JRadioButton("$50");
		creditPick[0].setActionCommand("50");
		creditPick[0].addActionListener(new CreditSizeListener());
		
		creditPick[1] = new JRadioButton("$100");
		creditPick[1].setActionCommand("100");
		creditPick[1].setSelected(true);
		creditPick[1].addActionListener(new CreditSizeListener());
		
		creditPick[2] = new JRadioButton("$200");
		creditPick[2].setActionCommand("200");
		creditPick[2].addActionListener(new CreditSizeListener());
		
		creditPick[3] = new JRadioButton("$500");
		creditPick[3].setActionCommand("500");
		creditPick[3].addActionListener(new CreditSizeListener());
		
		
		group2.add(creditPick[0]);
		group2.add(creditPick[1]);
		group2.add(creditPick[2]);
		group2.add(creditPick[3]);
		bottomPanel.add(creditPick[0]);
		bottomPanel.add(creditPick[1]);
		bottomPanel.add(creditPick[2]);
		bottomPanel.add(creditPick[3]);
		
		
		//this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(2, 1));
		this.add(topPanel);
		this.add(bottomPanel);
		this.setTitle("WagerModel and CreditModel settings.");
		//this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.pack();
		//this.setVisible(true);
	}
	
	/**
	 * The next 2 listeners listen for the radio buttons
	 * When new radio button is selected they fire an event and change the 
	 * states of the 4 provided variables from the WagerCreditView object
	 * @author Artyom M. a.k.a. artiyom
	 *
	 */
	class BetSizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			wagerModel.setWagerSize(Double.parseDouble(e.getActionCommand()));
			wagerLbl.setText(String.format("WAGER:  %.0f", wagerModel.getWagerSize()));
		}
		
	}
	
	class CreditSizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			creditModel.setAmount(Double.parseDouble(e.getActionCommand()));
			moneyLeft.setText(String.format("TOTAL MONEY:   %.0f", creditModel.getAmount()));
		}
		
	}

}
