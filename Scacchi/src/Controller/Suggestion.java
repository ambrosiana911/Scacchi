package Controller;

import Model.Board;

public class Suggestion {
	private final Board board;
	private final boolean legal;

	private final static int empty = 0;
	private final static int greenCell = 13;


	public Suggestion(Board board, int row, int col){

		this.board = board;
		this.legal = chooseSuggestion(row, col);
	}

	public boolean isLegal() {
		return legal;
	}

	//mette un suggerimento
	private void putSuggestion(int row, int col) {
		board.setPawnAt(row, col, greenCell + board.getCellAt(row, col));
	}

	//scelgo i suggerimenti in base alla pedina
	public boolean chooseSuggestion(int row, int col){
		
		switch (board.getCellAt(row,col)){
			case 1:						//blackPawn
				if(row + 1 < 8 && board.getCellAt(row+1, col) == empty)
					putSuggestion(row+1, col);
				if( row + 1 < 8){
					if(col - 1 >= 0 && board.isWhiteCell(row+1, col-1))
						putSuggestion(row+1, col-1);
					if(col + 1 < 8 && board.isWhiteCell(row+1, col+1))
						putSuggestion(row+1, col+1);
				}
				return true;
			case 2:
				return rookSuggestion(row,col,false);
			case 3:
				return horseSuggestion(row,col,false);
			case 4:
				return bishopSuggestion(row,col,false);
			case 5:
				return queenSuggestion(row,col,false);
			case 6:
				return new KingSuggestion(board, row,col,false).isLegal();
			case 7:
				if(row - 1 >= 0 && board.getCellAt(row-1, col) == empty)
					putSuggestion(row-1,col);
				if(row - 1 >= 0){
					if(col - 1 >= 0 && board.isBlackCell(row-1, col-1))
						putSuggestion(row-1,col-1);
					if(col + 1 < 8 && board.isBlackCell(row-1, col+1))
						putSuggestion(row-1,col+1);
				}
				return true;
			case 8:
				return rookSuggestion(row,col,true);
			case 9:
				return horseSuggestion(row,col,true);
			case 10:
				return bishopSuggestion(row,col,true);
			case 11:
				return queenSuggestion(row,col,true);
			case 12:
				return new KingSuggestion(board, row,col, true).isLegal();
			default:
				return false;
			}
	}
	
	private boolean rookSuggestion(int row, int col, boolean color){
		int counter = 0;
		
		for(int i = 1 ; col + i < 8 ; i++)
			if(board.isEmptyCell(row, col+i)){
				putSuggestion(row, col+i);
				counter++;
			}
			else if(board.cellColor(row, col+i) != color){
				putSuggestion(row, col+i);
				counter++;
				break;
			}
			else
				break;
		for(int i = 1 ; col - i >= 0 ; i++)
			if(board.isEmptyCell(row, col-i)){	
				putSuggestion(row, col-i);
				counter++;
			}
			else if(board.cellColor(row, col-i) != color){
				putSuggestion(row, col-i);
				counter++;		
				break;
			}
			else
				break;
		for(int i = 1 ; row + i < 8 ; i++)
			if(board.isEmptyCell(row+i, col)){
				putSuggestion(row+i, col);
				counter++;
			}
			else if(board.cellColor(row+i, col) != color){
				putSuggestion(row+i, col);
				counter++;				
				break;
			}
			else
				break;
		for(int i = 1 ; row - i >= 0 ; i++)
			if(board.isEmptyCell(row-i, col)){
				putSuggestion(row-i, col);
				counter++;
			}
			else if(board.cellColor(row-i, col) != color){
				putSuggestion(row-i, col);
				counter++;				
				break;
			}
			else
				break;
		
		return counter != 0;
	}
	
	private boolean horseSuggestion(int row, int col, boolean color){
		int counter = 0;
		
		if(col + 2 < 8){
			if(row - 1 >= 0 && (board.getCellAt(row-1, col+2) == empty || board.cellColor(row-1, col+2) != color)){
				putSuggestion(row-1 , col+2);
				counter++;
			}
			if(row + 1 < 8 && (board.getCellAt(row+1, col+2) == empty || board.cellColor(row+1, col+2) != color)){
				putSuggestion(row+1 , col+2);
				counter++;
			}
		}

		if(col - 2 >= 0){
			if(row - 1 >= 0 && (board.getCellAt(row-1, col-2) == empty || board.cellColor(row-1, col-2) != color)){
				putSuggestion(row-1 , col-2);
				counter++;
			}
			if(row + 1 < 8 && (board.getCellAt(row+1, col-2) == empty || board.cellColor(row+1, col-2) != color)){
				putSuggestion(row+1 , col-2);
				counter++;
			}
		}
		
		if( row + 2 < 8 ){
			if( col + 1 < 8 && (board.getCellAt(row+2, col+1) == empty || board.cellColor(row+2, col+1) != color)){
				putSuggestion(row+2,col+1);
				counter++;
			}
			if( col - 1 >= 0 && (board.getCellAt(row+2, col-1) == empty || board.cellColor(row+2, col-1) != color)){
				putSuggestion(row+2,col-1);
				counter++;
			}
		}

		if( row - 2 >= 0 ){
			if( col + 1 < 8 && (board.getCellAt(row-2, col+1) == empty || board.cellColor(row-2, col+1) != color)){
				putSuggestion(row-2,col+1);
				counter++;
			}
			if( col - 1 >= 0 && (board.getCellAt(row-2, col-1) == empty || board.cellColor(row-2, col-1) != color)){
				putSuggestion(row-2,col-1);
				counter++;
			}
		}
		
		return counter != 0;
	}
	
	private boolean bishopSuggestion(int row, int col, boolean color){
		int counter = 0;
		
		for(int i = 1 ; row + i < 8 && col - i >= 0 ; i++)
			if(board.isEmptyCell(row+i, col-i)){
				putSuggestion(row+i, col-i);
				counter++;
			}
			else{ 
				if(board.cellColor(row+i, col-i) != color){
					putSuggestion(row+i, col-i);
					counter++;
					break;
				}
				else
					break;
			}
		
		for(int i = 1 ; row - i >=  0 && col - i >= 0 ; i++)
			if(board.isEmptyCell(row-i, col-i)){
				putSuggestion(row-i, col-i);
				counter++;
			}
			else{ 
				if(board.cellColor(row-i, col-i) != color){
					putSuggestion(row-i, col-i);
					counter++;
					break;
				}
				else
					break;
			}
		
		for(int i = 1 ; row - i >= 0 && col + i < 8 ; i++)
			if(board.isEmptyCell(row-i, col+i)){
				putSuggestion(row-i, col+i);
				counter++;
			}
			else{ 
				if(board.cellColor(row-i, col+i) != color){
					putSuggestion(row-i, col+i);
					counter++;
					break;
				}
				else
					break;
			}
		
		for(int i = 1 ; row + i < 8 && col + i < 8 ; i++)
			if(board.isEmptyCell(row+i, col+i)){
				putSuggestion(row+i, col+i);
				counter++;
			}
			else{ 
				if(board.cellColor(row+i, col+i) != color){
					putSuggestion(row+i, col+i);
					counter++;
					break;
				}
				else
					break;
			}
		
		return counter != 0;
	}
	
	private boolean queenSuggestion(int row, int col, boolean color){
		rookSuggestion(row,col,color);
		bishopSuggestion(row,col,color);
		return true;
	}
}