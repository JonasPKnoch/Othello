package othello;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

/*
 * Holds all swing components, and thus all UI. Should realistically be
 * split into more classes, but the bloat was slow.
 * 
 * @author Jonas K
 */
public class OthelloFrame extends JPanel implements ActionListener {
	GameController controller;
	LoadedImages images;
	BoardPanel boardPanel;
	
	JFrame frame;
	JLayeredPane layered;
	
	JPanel titlePanel;
	JLabel titleLabel;
	JButton newGameButton;
	
	JPanel scorePanel;
	JLabel turnLabel;
	JLabel scoreBlackLabel;
	JLabel scoreWhiteLabel;
	
	JPanel gameOverPanel;
	JLabel gameOverLabel;
	JLabel finalScoreWhiteLabel;
	JLabel finalScoreBlackLabel;
	JLabel winnerLabel;
	
	/*
	 * Just initializes everything.
	 */
	OthelloFrame(GameController controller, LoadedImages images) {
		this.controller = controller;
		this.images = images;
		
		frame = new JFrame();
		frame.setIconImage(images.APP_ICON.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(800, 900);
		frame.setLayout(new BorderLayout());
		
		//This weirdness is so the "game over" message can display over top of the other UI.
		layered = new JLayeredPane();
		layered.setPreferredSize(new Dimension(800, 900));
		layered.setVisible(true);
		frame.add(layered);
		
		setLayout(new BorderLayout());
		setSize(800, 900);
		setBackground(Color.black);
		layered.add(this, 0);
		
		addTitle();
		addScores();
		
		boardPanel = new BoardPanel(this, controller, images);
		add(boardPanel, BorderLayout.PAGE_END);
		
		addGameOver();
		
		updateInfoUI();
		frame.pack();
	}
	
	/*
	 * Adds the title panel, which includes the "new game" button. Only
	 * used for initialization.  
	 */
	void addTitle() {
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(800, 50));
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		
		titleLabel = new JLabel(images.TITLEIMAGE);
		newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Arial", Font.PLAIN, 16));
		newGameButton.addActionListener(this);
		titlePanel.add(titleLabel);
		titlePanel.add(newGameButton);
		
		add(titlePanel, BorderLayout.PAGE_START);
	}
	
	/*
	 * Adds the scores panel, that displays the scores and the current
	 * turn. Only used for initialization. 
	 */
	void addScores() {
		scorePanel = new JPanel();
		scorePanel.setPreferredSize(new Dimension(800, 20));
		scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		
		scoreBlackLabel = new JLabel("");
		scoreBlackLabel.setFont(new Font("Arial", Font.BOLD, 20));
		turnLabel = new JLabel("");
		turnLabel.setFont(new Font("Arial", Font.BOLD, 20));
		scoreWhiteLabel = new JLabel("");
		scoreWhiteLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		scorePanel.add(scoreBlackLabel);
		scorePanel.add(turnLabel);
		scorePanel.add(scoreWhiteLabel);
		add(scorePanel, BorderLayout.CENTER);
	}
	
	/*
	 * Adds the "game over" panel, which displays the game over 
	 * message, the final scores, and the winner. Is attached directly 
	 * to layered so that it can be displayed over top of all other UI.
	 * Starts invisible. Only used for initialization. 
	 */
	void addGameOver() {
		gameOverPanel = new JPanel(new BorderLayout(50, 0));
		gameOverPanel.setBounds(225, 250, 350, 275);
		gameOverPanel.setBackground(Color.white);
		gameOverPanel.setVisible(false);
		gameOverPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		
		gameOverLabel = new JLabel(images.GAME_FINISHED_IMAGE);
		gameOverLabel.setBounds(0, 0, 600, 400);
		
		finalScoreBlackLabel = new JLabel("");
		finalScoreBlackLabel.setHorizontalAlignment(JLabel.CENTER);
		finalScoreBlackLabel.setVerticalAlignment(JLabel.CENTER);
		finalScoreBlackLabel.setPreferredSize(new Dimension(200, 100));
		finalScoreBlackLabel.setFont(new Font("Arial", Font.BOLD, 16));
		finalScoreWhiteLabel = new JLabel("");
		finalScoreWhiteLabel.setHorizontalAlignment(JLabel.CENTER);
		finalScoreWhiteLabel.setVerticalAlignment(JLabel.CENTER);
		finalScoreWhiteLabel.setPreferredSize(new Dimension(200, 100));
		finalScoreWhiteLabel.setFont(new Font("Arial", Font.BOLD, 16));
		winnerLabel = new JLabel("");
		winnerLabel.setHorizontalAlignment(JLabel.CENTER);
		winnerLabel.setPreferredSize(new Dimension(200, 100));
		winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
		
		gameOverPanel.add(gameOverLabel, BorderLayout.PAGE_START);
		gameOverPanel.add(finalScoreBlackLabel, BorderLayout.WEST);
		gameOverPanel.add(finalScoreWhiteLabel, BorderLayout.EAST);
		gameOverPanel.add(winnerLabel, BorderLayout.PAGE_END);
		layered.add(gameOverPanel, JLayeredPane.POPUP_LAYER);
	}
	
	/*
	 * Sets the text for all the labels in scorePanel. 
	 */
	void updateInfoUI() {
		scoreBlackLabel.setText("Black: "+controller.playerScores[0]);
		String turn;
		switch(controller.turn) {
		case BLACK:
			turn = "Black";
			break;
		case WHITE: 
			turn = "White";
			break;
		default:
			turn = null;
			break;
		}
		this.turnLabel.setText("Turn: "+turn);
		scoreWhiteLabel.setText("White: "+controller.playerScores[1]);
		
		if(controller.gameFinished) {
			gameOverPanel.setVisible(true);
			
			int scoreBlack = controller.playerScores[0];
			int scoreWhite = controller.playerScores[1];
			finalScoreBlackLabel.setText("Black Score: "+scoreBlack);
			finalScoreWhiteLabel.setText("White Score: "+scoreWhite);
			String winner;
			if(scoreBlack > scoreWhite) {
				winner = "Black";
			} else if(scoreWhite > scoreBlack) {
				winner = "White";
			} else {
				winner = "Tie";
			}
			winnerLabel.setText("Winner: "+ winner);
		} else {
			gameOverPanel.setVisible(false);
		}
		
		revalidate();
	}
	
	/*
	 * Resets everything in the game. Should probably be in another 
	 * class.
	 */
	void resetGame() {
		controller =  new GameController(new Board(controller.board.width, controller.board.height));
		boardPanel.controller = controller;
		boardPanel.updateSquares();
		updateInfoUI();
	}
	
	/*
	 * Resets the game when the "new game" button is clicked.
	 */
	public void actionPerformed(ActionEvent e) {
		resetGame();
	}
}
