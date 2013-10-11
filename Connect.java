import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Connect extends JPanel {
    private enum Disc {
	RED, BLACK, EMPTY;
    }
    private Disc[][] board = new Disc[6][7];
    private Disc turn = Disc.RED;
    private int[] lowest = {6, 6, 6, 6, 6, 6};
    private int redWins = 0;
    private int blackWins = 0;
    public Connect() {
	for (int i = 0; i<board.length; i++) {
	    for (int j = 0; j<board[i].length; j++) {
		board[i][j] = Disc.EMPTY;
	    }
	}
	turn = Disc.RED;
	this.addMouseListener(new myMouseHandler());
	this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void setBoard(Disc[][] newBoard) {
	this.board = newBoard;
    }

    public String getTurn() {
	return (turn == Disc.RED) ? "Red" : "Black";
    }

    public void incWins(Disc whoWon) {
	if (whoWon == Disc.RED) redWins++;
	else blackWins++;
    }

    public int getRedWins() {
	return redWins;
    }

    public int getBlackWins() {
	return blackWins;
    }

    private void redrawBoard() {
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[i].length; j++) {
		board[i][j] = Disc.EMPTY;
	    }
	}
	for (int i = 0; i < 6; i++) lowest[i] = 6;
	repaint();
    }
    private void insertDisc(int x) {
	// change so that the number is x/40, can put it in one statement with an initial check to make sure the value is within the bounds
	if (x > 0 && x < 40 && lowest[0] >= 0) { 
	    board[0][lowest[0]] = turn;
	    lowest[0]--;
	    repaint();
	    this.checkWin();
	    turn = (turn == Disc.RED)? Disc.BLACK : Disc.RED;
	}
	if (x > 40 && x < 80 && lowest[1] >= 0) {
	    board[1][lowest[1]] = turn;
	    lowest[1]--;
	    repaint();
	    this.checkWin();
	    turn = (turn == Disc.RED)? Disc.BLACK : Disc.RED;
	}
	if (x > 80 && x < 120 && lowest[2] >= 0) {
	    board[2][lowest[2]] = turn;
	    lowest[2]--;
	    repaint();
	    this.checkWin();
	    turn = (turn == Disc.RED)? Disc.BLACK : Disc.RED;
	}
	if (x > 120 && x < 160 && lowest[3] >= 0) {
	    board[3][lowest[3]] = turn;
	    lowest[3]--;
	    repaint();
	    this.checkWin();
	    turn = (turn == Disc.RED)? Disc.BLACK : Disc.RED;
	}
	if (x > 160 && x < 200 && lowest[4] >= 0) {
	    board[4][lowest[4]] = turn;
	    lowest[4]--;
	    repaint();
	    this.checkWin();
	    turn = (turn == Disc.RED)? Disc.BLACK : Disc.RED;
	}
	if (x > 200 && x < 240 && lowest[5] >= 0) {
	    board[5][lowest[5]] = turn;
	    lowest[5]--;
	    repaint();
	    this.checkWin();
	    turn = (turn == Disc.RED)? Disc.BLACK : Disc.RED;
	}
    }

    public void checkWin() {
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[i].length; j++) {
		if (i < board.length-3) {
		    if (board[i][j] == turn && board[i+1][j] == turn && board[i+2][j] == turn && board[i+3][j] == turn) {
			String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
			incWins(turn);
			JOptionPane.showMessageDialog(this, wins);
			this.redrawBoard();
		    }
		}
		if (i < board.length && j < board[i].length-2) {
		    if (board[i][j] == turn && board[i][j+1] == turn && board[i][j+2] == turn && board[i][j+3] == turn) {
			String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
			incWins(turn);
			JOptionPane.showMessageDialog(this, wins);
			this.redrawBoard();
		    }
		}
		if (i > 3 && j > 3) {
		    if (board[i][j] == turn && board[i-1][j-1] == turn && board[i-2][j-2] == turn && board[i-3][j-3] == turn) {
			String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
			incWins(turn);
			JOptionPane.showMessageDialog(this, wins);
			this.redrawBoard();
		    }
		}

	    }
	}
	for (int i = 0; i < board.length-3; i++) {
	    for (int j = 6; j > 2; j--) {
		if (board[i][j] == turn && board[i+1][j-1] == turn && board[i+2][j-2] == turn && board[i+3][j-3] == turn) {
		    String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
		    incWins(turn);
		    JOptionPane.showMessageDialog(this, wins);
		    this.redrawBoard();
		}
	    }
	}

	/*	for (int i = 0; i < board.length-3; i++) {
	    for (int j = 0; j < board[i].length; j++) {
		if (board[i][j] == turn && board[i+1][j] == turn && board[i+2][j] == turn && board[i+3][j] == turn) {
		    String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
		    incWins(turn);
		    //System.out.println(wins);
		    JOptionPane.showMessageDialog(this, wins);
		    this.redrawBoard();
		}
	    }
	}
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[i].length-3; j++) {
		if (board[i][j] == turn && board[i][j+1] == turn && board[i][j+2] == turn && board[i][j+3] == turn) {
		    String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
		    incWins(turn);
		    //System.out.println(wins);
		    JOptionPane.showMessageDialog(this, wins);
		    this.redrawBoard();
		}
	    }
	}
	for (int i = 3; i < board.length; i++) {
	    for (int j = 3; j < board[i].length; j++) {
		if (board[i][j] == turn && board[i-1][j-1] == turn && board[i-2][j-2] == turn && board[i-3][j-3] == turn) {
		    String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
		    incWins(turn);
		    //System.out.println(wins);
		    JOptionPane.showMessageDialog(this, wins);
		    this.redrawBoard();
		}
	    }
	}
	for (int i = 0; i < board.length-3; i++) {
	    for (int j = 6; j > 2; j--) {
		if (board[i][j] == turn && board[i+1][j-1] == turn && board[i+2][j-2] == turn && board[i+3][j-3] == turn) {
		    String wins = (turn == Disc.RED) ? "Red wins!" : "Black wins!";
		    incWins(turn);
		    //System.out.println(wins);
		    JOptionPane.showMessageDialog(this, wins);
		    this.redrawBoard();
		}
	    }
	}
	*/

	for (int i = 0; i < lowest.length; i++) {
	    if (lowest[i] >= 0) return;
	}
	JOptionPane.showMessageDialog(this, "Tie game!");
	this.redrawBoard();
    }





    private class myMouseHandler implements MouseListener {

	public void mousePressed(MouseEvent e) {
	    int clicked = e.getX();
	    insertDisc(clicked);
	}

	public void mouseReleased(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setBackground(Color.YELLOW);
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[i].length; j++)
		{
		    g2.setPaint(Color.YELLOW);
		    g2.fill(new Rectangle2D.Double(i*40, j*40, 40, 40));
		    if (board[i][j] == Disc.BLACK) g2.setPaint(Color.BLACK);
		    else if (board[i][j] == Disc.RED) g2.setPaint(Color.RED);
		    else g2.setPaint(Color.WHITE);
		    g2.fill(new Ellipse2D.Double(i*40, j *40, 40, 40));
		}
	}
	g2.setPaint(Color.BLACK);
	g2.setFont(new Font("Serif", Font.PLAIN, 18)); 
	g2.drawString("Current turn: " + turn, 10, 300);
	g2.drawString("Red wins: " + getRedWins(), 10, 320);
	g2.drawString("Black wins: " + getBlackWins(), 10, 340);
	
    }
    public static void main(String[] args) {
	JFrame game = new JFrame("CONNECT!");
	game.setPreferredSize(new Dimension(240, 350));
	game.setBackground(Color.YELLOW);
	game.setResizable(false);
	Connect c = new Connect();
	game.add(c);
	game.pack();
	game.setVisible(true);
	game.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}