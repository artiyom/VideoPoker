import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This is the game's main class that will be called by OS
 * It creates all the main objects of the game (PlayerCards, PlayerCardsView, DealButtonPanel)
 * All the objects are created here to make sure that every module of the game is using the exactly 
 * same object.
 * Then this object adds all other objects to the main Frame so they get displayed.
 * @author Artyom M. a.k.a. artiyom
 *
 */

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	private PlayerCards playerCards = new PlayerCards();
	private PlayerCardsView cardPanel;
	
	private ReturnTableView infoPanel;
	private ReturnTableController returnFactor;
	
	
	private DealButtonPanel control;
	
	private Wager wager = new Wager(5);
	private Credit credit = new Credit(500);
	private WagerCreditView bet;
	
	//private GameLogic logic;
	
	
	
	
	private JPanel buttomPanel = new JPanel();
	public Game() {
		bet = new WagerCreditView(credit, wager);
		infoPanel = new ReturnTableView();
				
		returnFactor = new ReturnTableController(infoPanel, credit);
		
		cardPanel = new PlayerCardsView(playerCards);
	
		control = new DealButtonPanel(playerCards, cardPanel, infoPanel, credit, bet, wager, returnFactor);
		
		this.setLayout(new BorderLayout());
		
		this.add(infoPanel, BorderLayout.NORTH);
		this.add(cardPanel, BorderLayout.CENTER);
		this.add(buttomPanel, BorderLayout.SOUTH);
		buttomPanel.setPreferredSize(new Dimension(100, 100));
		buttomPanel.setLayout(new GridLayout(1, 3));
		buttomPanel.add(bet);
		buttomPanel.add(returnFactor);
		buttomPanel.add(control);
		
		buttomPanel.setBackground(new Color(0, 0, 100));

		/**
		 * The program starts in the middle of the screen.
		 */
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(dimension.width/4, dimension.height/4);
		
	}

	public static void main(String[] args) {
		Game window = new Game();
		window.setTitle("Jacks or Better. Demo");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.pack();

	}

}
