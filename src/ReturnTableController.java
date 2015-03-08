import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class is a Controller class for the ReturnTable (Model Class)
 * It sets which column is currently provides return factors
 * @author Artyom M. a.k.a. artiyom
 *
 */
public class ReturnTableController extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel betLabel;
	
	private JButton leftBtn = new JButton("Bet Down");
	private JButton rightBtn = new JButton("Bet Up");
	
	
	
	private int bet;
	public ReturnTableController(ReturnTableView table, Credit credit) {
		this.bet = table.getFactor();
		if (bet == 1 || bet == 5) {
			leftBtn.setEnabled(false);
		}

		this.setBackground(new Color(0, 0, 100));
		this.betLabel = new JLabel("Bet: " + bet);
		this.setLayout(new FlowLayout());
		this.add(leftBtn);
		
		leftBtn.setHorizontalAlignment(JButton.LEFT);
		leftBtn.setFocusable(false);
		
		this.add(betLabel);
		betLabel.setHorizontalAlignment(JLabel.CENTER);
		betLabel.setForeground(Color.WHITE);
		
		this.add(rightBtn);
		rightBtn.setHorizontalAlignment(JButton.RIGHT);
		rightBtn.setFocusable(false);
		
		/**
		 * The 2 following buttons control the table.
		 * Right button pressed - the next column is being painted red
		 * and it's set to be the current return column
		 * The same with the Left Button
		 */
		leftBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rightBtn.setEnabled(true);
				if (bet > 2) {
					bet--;
					betLabel.setText("Bet: " + bet);
					table.lowerBet();
				}
				else {
					bet--;
					betLabel.setText("Bet: " + bet);
					table.lowerBet();
					leftBtn.setEnabled(false);
				}
				
				
			}
			
		});
		
		rightBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				leftBtn.setEnabled(true);
				if (bet < 4) {
					bet++;
					betLabel.setText("Bet: " + bet);
					table.higherBet();
				}
				else {
					bet++;
					betLabel.setText("Bet: " + bet);
					table.higherBet();
					rightBtn.setEnabled(false);
					
				}
				
				
			}	
			
		});
		
	}
	
	/**
	 * Enable/Disable = this method are used during the game
	 * when Deal button is pressed and new cards dealt, player can't change the return table or the bet
	 */
	public void disableButtons() {
		this.leftBtn.setEnabled(false);
		this.rightBtn.setEnabled(false);
	}
	public void enableButtons() {
		this.leftBtn.setEnabled(true);
		this.rightBtn.setEnabled(true);
	}

}