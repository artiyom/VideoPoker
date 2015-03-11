package viewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.CardModel;

/**
 * This class is a GUI for the CardModel class
 * It extends JPanel and contains 2 labels one for the "Hold" label the other one - for the card
 * The constructor checks what card is assign and gets the sufficient image from the resources. 
 * The "Hold" label gets visible and invisible when clicking on the object. 
 * @see CardModel
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class CardViewController extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel cardLbl = new JLabel();
	private JLabel holdLbl = new JLabel();
	
	private URL url;//the address of the Image
	private ImageIcon icon;
	
	
	public CardViewController() {
		try {
			//System.out.println("/cards/b2fv.png");
			this.url = CardViewController.class.getResource("/b2fv.png");
		}
		catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "The card image wasn't found.");
			return;
		}
		this.icon = new ImageIcon(url);
		
		this.setInfromation();
	}
	
	/**
	 * The main constructor. It gets the CardModel object and assigns the Image to it.
	 * @param cardModel
	 */
	public CardViewController(CardModel cardModel) {
		
		
		try {
			//System.out.println("cards/" + card.getSuitName() + "/" + card.getValueName() + ".png");
			this.url = CardViewController.class.getResource("/" + cardModel.getSuitName() + "/" + cardModel.getRankName() + ".png");
			
		}
		catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "The card image wasn't found.");
			return;
		}
		this.icon = new ImageIcon(url);

		/**
		 * This listener detects if the mouse was clicked over the Object
		 * if was clicked the class must be hold/unhold 
		 */
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cardModel.isHeld()) {
					cardModel.unhold();
					CardViewController.this.holdLbl.setText("");
				}
				else if (cardModel.isHoldable() && !cardModel.isHeld()) {
					cardModel.hold();
					CardViewController.this.holdLbl.setText("Hold");
				}
				
			}
			
			
			
		});
		this.setInfromation();

	}
	/**
	 * This private method sets the look of this panel
	 */
	private void setInfromation() {
		//holdLbl.setOpaque(true);
		holdLbl.setHorizontalAlignment(JLabel.CENTER);
		//holdLbl.setVerticalAlignment(JLabel.TOP);
		holdLbl.setForeground(new Color(252, 226, 0));
		//holdLbl.setBackground(Color.BLACK);
		//holdLbl.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		holdLbl.setPreferredSize(new Dimension(0, 25));
		cardLbl.setHorizontalAlignment(JLabel.CENTER);
		cardLbl.setVerticalAlignment(JLabel.BOTTOM);
		cardLbl.setIcon(icon);		
		cardLbl.setForeground(new Color(254, 252, 0));
		//cardLbl.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		this.setBackground(new Color(0, 0, 100));
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new BorderLayout());
		this.add(holdLbl, BorderLayout.PAGE_START);
		this.add(cardLbl, BorderLayout.SOUTH);

		
	}

}
