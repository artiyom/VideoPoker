import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This is Graphical class that displays the bet size and the money left
 * It's a view class for 2 model classed: Wager and Credit.
 * @see Wager
 * @see Credit
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class WagerCreditView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel moneyLeft = new JLabel();
	private JLabel wagerLbl = new JLabel();
	private JButton changeBtn = new JButton("...");
	private WagerCreditSettingWindow dialog;
	private Credit credit;
	private Wager wager;
	
	public WagerCreditView(Credit credit, Wager wager) {
		this.credit = credit;
		this.wager = wager;
		this.dialog = new WagerCreditSettingWindow(credit, wager, wagerLbl, moneyLeft);
		updateLabeles();
	}
	
	/**
	 * Updates the entire panel, by removing everything and adding back again
	 * This method is called in the game whenever any of 2 labels are needed to be updated
	 */
	public void updateLabeles() {
		this.removeAll();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(wagerLbl);
		this.add(moneyLeft);
		this.add(changeBtn);
		
		this.setBackground(new Color(0, 0, 100));
		wagerLbl.setText(String.format("WAGER:  %.0f", wager.getWagerSize()));
		wagerLbl.setForeground(Color.white);
		
		moneyLeft.setText(String.format("TOTAL MONEY:   %.0f", credit.getAmount()));
		moneyLeft.setForeground(Color.white);
		changeBtn.setFocusable(false);
		changeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
			
		});
	}
	public double getBetSize() {
		return this.wager.getWagerSize();
	}
	
	/**
	 * This 2 methods are used after Deal button is pressed, so nothing can be changed while 
	 * the player is in the middle of the game
	 */
	public void disableButtons() {
		this.changeBtn.setEnabled(false);
	}
	public void enableButtons() {
		this.changeBtn.setEnabled(true);
	}

}
