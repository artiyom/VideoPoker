package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * This class extends JLabel and is used in the program (in the ReturnTableView) to demonstrate 
 * the labels - the once that reprsent HandModel Names and return factors. (top part of the GUI). This
 * label will start/stop blinking if sufficient methods are called. The blinking effect is used 
 * for those cases when this label represents the Wining hand and it's return value. On Casino 
 * Slot Machines the blinking effect is also used.
 * @see ReturnTableView
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class BlinkingLabelView extends JLabel {
	
	private static final long serialVersionUID = 1L;
	private Font font; //the font of this label
	/**
	 * The timer object is used here to set the blinking frequency
	 */
	private Timer timer;
	
	//the same as JLabel()
	public BlinkingLabelView() {
		super();
		setDefaultSettings();
	}
	
	//the same as JLabel("label")
	public BlinkingLabelView(String string) {
		super(string);
		setDefaultSettings();
	}
	
	private void setDefaultSettings() {
		timer = new Timer(400, new TimerListener());
		
		font = this.getFont();
		this.setForeground(new Color(254, 252, 0));
		this.setFont(new Font(font.getName(), Font.BOLD, 16));
	}

	public void startBlinking() {
		this.timer.start();
	}
	public void stopBlinking() {
		this.timer.stop();
		/**
		 * after the timer is stopped the color of the JLabel is set to the oroginal color
		 */
		this.setForeground(new Color(254, 252, 0));
	}
	
	class TimerListener implements ActionListener {
		/**
		 * Once the startBlinking() method is used the code checks what color
		 * the label is, and then chnages the color to the other color,
		 * and then the other way around until stopBlinking() is used.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (BlinkingLabelView.this.getForeground().equals(Color.WHITE)) {
				BlinkingLabelView.this.setForeground(new Color(254, 252, 0));
			}
			else {
				BlinkingLabelView.this.setForeground(Color.WHITE);
			}
			
		}
		
	}

}
