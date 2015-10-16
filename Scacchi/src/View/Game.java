package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.Board;
import Controller.KingSuggestion;
import Controller.Suggestion;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Game extends JFrame {
	private final Board board;
	private Elements element = new Elements();
	private final int empty = 0;
	private int turn = 0;
	
	public Game(Board board){
		super("Scacchi");

		this.board = board;

		setLayout(new GridLayout(8,8));
		setBounds(170, 100, 600, 550);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(500, 500);

		createMenu();
		showBoard();

		// chiudere la finestra
		this.addWindowListener(new WindowListener() {

			public void windowClosing(WindowEvent e) {
				new CheckExit().setVisible(true);
			}

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {} 

		});
	}

	// mostra la scacchiera
	private void showBoard(){

		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++){
				addCell( new Cell(decideColor(row,col) ? Color.WHITE:Color.DARK_GRAY, board.getCellAt(row, col)), row, col );
			}
	}
	
	//decide il colore della cella
	private boolean decideColor(int x, int y){
		if( (x+y) % 2 == 0)
			return true;
		else
			return false;
	}
	
	private void addCell(Cell cell, final int row, final int col){
		add(cell);
		
		cell.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){			
				
				if(turn == 0 && board.isWhiteCell(row, col)){
					resetPawns();
					if(board.whiteUnderCheck == 0){
						addSuggestion(board, row, col);
						board.setPawnToMove(board.getCellAt(row, col),row,col);
					}
					else
						if(board.getCellAt(row, col) == 12){
							addSuggestion(board, row, col);
							board.setPawnToMove(board.getCellAt(row, col),row,col);
							
						}
				}
				else if(turn == 1 && board.isBlackCell(row, col)){
					resetPawns();
					if(board.blackUnderCheck == 0){
						addSuggestion(board, row, col);
						board.setPawnToMove(board.getCellAt(row, col),row,col);
					}
					else
						if(board.getCellAt(row, col) == 6){
							addSuggestion(board, row, col);
							board.setPawnToMove(board.getCellAt(row, col),row,col);
							
						}
				}
				else if(board.getCellAt(row, col) >= 13 && board.getCellAt(row, col) < 26){
					turn = (turn + 1) % 2;
					
					Elements.destroyPanel();
					createMenu();
					
					resetPawns();
					moveAt(row,col,board.getPawnToMove());
					
					if(board.getPawnToMove() == 12)
						board.whiteUnderCheck = 0;
					if(board.getPawnToMove() == 6)
						board.blackUnderCheck = 0;
				}
			}

		});
	}

	// aggiunge un suggerimento
	private void addSuggestion(Board board, int x, int y) {
		
		if( new Suggestion(board, x, y).isLegal() ){
			getContentPane().removeAll();
			showBoard();
			
			invalidate();
			validate();

		}else
			return;
	}
	
	//sposta la pedina
	private void moveAt(int row, int col, int pedina) {
		resetPawns();
		
		board.setPawnAt(row, col, pedina);
		board.setPawnAt(board.getRowFrom(), board.getColFrom(), empty);
		
		getContentPane().removeAll();
		showBoard();
		
		invalidate();
		validate();
		
		if(!(new KingSuggestion(board, board.rowWhiteKing(), board.colWhiteKing(), true)).isLegal()
				&& checkmate(board.rowWhiteKing(), board.colWhiteKing()))
			new winner(false).setVisible(true);
		resetPawns();
		if(!(new KingSuggestion(board, board.rowBlackKing(), board.colBlackKing(), false)).isLegal() 
				&& checkmate(board.rowBlackKing(), board.colBlackKing()) )
			new winner(true).setVisible(true);
		resetPawns();	
		
		new Suggestion(board, row, col).chooseSuggestion(row, col);
		if(board.getCellAt(board.rowBlackKing(), board.colBlackKing())> 13 && board.getCellAt(board.rowBlackKing(), board.colBlackKing()) % 13 == 6){
			resetPawns();
			board.blackUnderCheck = 1;
			board.setPawnAt(board.rowBlackKing(), board.colBlackKing(), 39 + 6);
			board.setPawnAt(row, col, 39 + pedina );
			getContentPane().removeAll();
			showBoard();	
			invalidate();
			validate();
		}
		else if(board.getCellAt(board.rowWhiteKing(), board.colWhiteKing())> 13 && board.getCellAt(board.rowWhiteKing(), board.colWhiteKing()) % 13 == 12){
			resetPawns();
			board.whiteUnderCheck = 1;
			board.setPawnAt(board.rowWhiteKing(), board.colWhiteKing(), 39 + 12);
			board.setPawnAt(row, col, 39 + pedina);
			getContentPane().removeAll();
			showBoard();	
			invalidate();
			validate();
		}
		resetPawns();
	}
	
	//se le celle sono verdi non vuote il loro valore viene riportato al valore della cella senza sfondo verde
	private void resetPawns() {
		for(int col = 0; col < 8 ; col++ )
			for(int row = 0 ; row < 8 ; row++)
				if(board.getCellAt(row, col) >= 13)
					board.setPawnAt(row, col, board.getCellAt(row, col) % 13);
	}

	//verifica se c'è scacco matto
	private boolean checkmate(int row, int col){
		
		if(row - 1 >= 0)
			if(board.getCellAt(row-1, col) >= 13)
				return true;
		if(row - 1 >= 0 && col + 1 < 8)
			if(board.getCellAt(row-1, col+1) >= 13)
				return true;
		if(col + 1 < 8)
			if(board.getCellAt(row, col+1) >= 13)
				return true;
		if(row + 1 < 8 && col + 1 < 8)
			if(board.getCellAt(row+1, col+1) >= 13)
				return true;
		if(row + 1 < 8)
			if(board.getCellAt(row+1, col) >= 13)
				return true;
		if(row + 1 < 8 && col - 1 >= 0)
			if(board.getCellAt(row+1, col-1) >= 13)
				return true;
		if(col- 1 >= 0)
			if(board.getCellAt(row, col-1) >= 13)
				return true;
		if(row - 1 >= 0 && col - 1 >= 0 )
			if(board.getCellAt(row-1, col-1) >= 13)
				return true;
		return false;		
	}
	
	// costruzione del Menu
	private void createMenu() {
		JMenuBar barr = new JMenuBar();	
		barr.add(element.createTitle(turn));	
		setJMenuBar(barr);
	} 

	// il main
	public static void main(String[] args) {
		new Game(new Board()).setVisible(true);	
	}
}