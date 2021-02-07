package othello;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

/*
 * A GUI element responsible for the displayed board itself. Adjusts 
 * icons according to controller, and handles input on the board. 
 * 
 * @author Jonas K
 */
public class BoardPanel extends JPanel{
	GameController controller;
	private LoadedImages files;
	private JPanel[][] gridSquares;
	private OthelloFrame frame;

	/*
	 * Constructs a default board. Sets up a bunch of Swing stuff.
	 */
	BoardPanel (OthelloFrame frame, GameController controller, LoadedImages files) {
		this.frame = frame;
		this.controller = controller;
		this.files = files;
		
		setLayout(new GridLayout(controller.board.width, controller.board.height));
		setSize(new Dimension(800, 800));
		
		gridSquares = new JPanel[controller.board.width][controller.board.height];
		for(int i = 0; i < controller.board.width; i++) {
			for(int j = 0; j < controller.board.height; j++) {
				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
				panel.add(new JLabel(files.BOARDSQUAREIMAGE));
				panel.setPreferredSize(new Dimension(100, 100));
				gridSquares[i][j] = panel;
				add(panel);
			}
		}
		
		updateSquares();
	}
	
	/*
	 * Loops over the board and changes all labels/buttons according to
	 * the state of the controller. Very inefficient way of doing it,
	 * but it works well enough for the assignment.
	 */
	void updateSquares() {
		for(int i = 0; i < controller.board.width; i++) {
			for(int j = 0; j < controller.board.height; j++) {
				JPanel current = gridSquares[i][j];
				current.removeAll();
				if(controller.availableMoves[i][j] != null) {
					MoveButton button = new MoveButton(i, j);
					button.setIcon(files.MOVEIMAGE);
					current.add(button);
				} else if(controller.board.pieces[i][j] != null) {
					switch(controller.board.pieces[i][j].team) {
					case WHITE:
						current.add(new JLabel(files.WHITEPIECEIMAGE));
						break;
					case BLACK:
						current.add(new JLabel(files.BLACKPIECEIMAGE));
						break;
					}
				} else {
					current.add(new JLabel(files.BOARDSQUAREIMAGE));
				}
			}
		}
		revalidate();
	}
	
	/*
	 * Tells all relevant classes when a square has been clicked and 
	 * updates UI.
	 */
	private void moveClicked(int x, int y) {
		controller.takeTurn(x, y);
		updateSquares();
		frame.updateInfoUI();
	}
	
	/*
	 * A button specifically for moves on the board.
	 */
	private class MoveButton extends JButton implements ActionListener {
		int x;
		int y;
		
		/*
		 * Constructs the button given board coordinates.
		 */
		MoveButton(int x, int y) {
			this.x = x;
			this.y = y;
			addActionListener(this);
		}
		
		/*
		 * Just calls moveClicked with its coordinates.
		 */
		public void actionPerformed(ActionEvent e) {
			moveClicked(x, y);
		}
	}
}

