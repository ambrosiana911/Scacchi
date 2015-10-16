package Controller;

import Model.Board;

public class KingSuggestion{
	private Board board;
	private int row, col;
	private boolean color; 
	private final int greenCell = 13;
	
	public KingSuggestion(Board board, int row, int col, boolean color){
		this.board = board;
		this.row = row;
		this.col = col;
		this.color = color;
		
		setCell();
	}
	
	public boolean isLegal(){
		return setKingSuggestion();
	}
	
	//mette un suggerimento
	private void putSuggestion(int row, int col) {
		board.setPawnAt(row, col, greenCell + board.getCellAt(row, col));
	}
	
	//imposto la destinazione di ogni cella
	private void setCell(){
		for(int col = 0; col < 8 ; col++ )
			for(int row = 0 ; row < 8 ; row++)
				if(!board.isEmptyCell(row, col) && color != board.cellColor(row, col))
					switch (board.getCellAt(row,col)){
						case 1:						//blackPawn
							if( row + 1 < 8){
								if(col - 1 >= 0 && (board.isEmptyCell(row+1, col-1) 
												|| (!board.isEmptyCell(row+1, col-1) 
												&& board.cellColor(row+1, col-1) != color 
												&& board.getCellAt(row+1, col-1) < 26 
												&& isKingPossibleNextMove(row+1, col-1))))
									board.setPawnAt(row+1, col-1, 26 + board.getCellAt(row+1, col-1));
								if(col + 1 < 8 && (board.isEmptyCell(row+1, col+1)
												|| (!board.isEmptyCell(row+1, col+1)
												&& board.cellColor(row+1, col+1) != color
												&& board.getCellAt(row+1, col+1) < 26 
												&& isKingPossibleNextMove(row+1, col+1))))
									board.setPawnAt(row+1, col+1, 26 + board.getCellAt(row+1, col+1));
							}
							break;
						case 2:
							rookSuggestion(row,col);
							break;
						case 3:
							horseSuggestion(row,col);
							break;
						case 4:
							bishopSuggestion(row,col);
							break;
						case 5:
							queenSuggestion(row,col);
							break;
						case 6:
							kingSuggestion(row, col);
							break;
						case 7:
							if(row - 1 >= 0){
								if(col - 1 >= 0 && (board.isEmptyCell(row-1, col-1)
												|| (!board.isEmptyCell(row-1, col-1)
												&& board.cellColor(row-1, col-1) != color
												&& board.getCellAt(row-1, col-1) < 26 
												&& isKingPossibleNextMove(row-1, col-1))))
									board.setPawnAt(row-1,col-1,26 + board.getCellAt(row-1, col-1));
								if(col + 1 < 8 && (board.isEmptyCell(row-1, col+1)
												|| (!board.isEmptyCell(row-1, col+1)
												&& board.cellColor(row-1, col+1) != color
												&& board.getCellAt(row-1, col+1) < 26 
												&& isKingPossibleNextMove(row-1, col+1))))
									board.setPawnAt(row-1,col+1,26 + board.getCellAt(row-1, col+1));
							}
							break;
						case 8:
							rookSuggestion(row,col);
							break;
						case 9:
							horseSuggestion(row,col);
							break;
						case 10:
							bishopSuggestion(row,col);
							break;
						case 11:
							queenSuggestion(row,col);
							break;
						case 12:
							kingSuggestion(row,col);
							break;
						default:
							break;
					}
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++){
				if(board.isOrangeCell(row, col) && !isKingPossibleNextMove(row, col))
					board.setPawnAt(row, col, 0);
			}
	}
	
	private void rookSuggestion(int row, int col){

		for(int i = 1 ; col + i < 8 ; i++)
			if(board.isEmptyCell(row, col+i) || board.isOrangeCell(row, col+i))
				board.setPawnAt(row, col+i, 26);
			else if(!board.isEmptyCell(row, col+i) && 
					board.cellColor(row, col+i) != color && 
					board.getCellAt(row, col+i) < 26 && 
					isKingPossibleNextMove(row, col+i)){
				board.setPawnAt(row, col+i, 26 + board.getCellAt(row, col+i));
				break;
			}
			else if(row == this.row && col+i == this.col){}
			else
				break;
		
		for(int i = 1 ; col - i >= 0 ; i++)
			if(board.isEmptyCell(row, col-i) || board.isOrangeCell(row, col-i))
				board.setPawnAt(row, col-i, 26);
			else if(!board.isEmptyCell(row, col-i) && 
					board.cellColor(row, col-i) != color && 
					board.getCellAt(row, col-i)< 26 && 
					isKingPossibleNextMove(row, col-i)){
				board.setPawnAt(row, col-i, 26 + board.getCellAt(row, col-i));
				break;
			}
			else if(row == this.row && col-i == this.col){}
			else
				break;
		
		for(int i = 1 ; row + i < 8 ; i++)
			if(board.isEmptyCell(row+i, col) || board.isOrangeCell(row+i, col))
				board.setPawnAt(row+i, col, 26);
			else if(!board.isEmptyCell(row+i, col) && 
					board.cellColor(row+i, col) != color && 
					board.getCellAt(row+i, col) < 26 && 
					isKingPossibleNextMove(row+i, col)){
				board.setPawnAt(row+i, col, 26 + board.getCellAt(row+i, col));
				break;
			}
			else if(row+i == this.row && col == this.col){}
			else
				break;
		
		for(int i = 1 ; row - i >= 0 ; i++)
			if(board.isEmptyCell(row-i, col) || board.isOrangeCell(row-i, col))
				board.setPawnAt(row-i, col, 26);
			else if(!board.isEmptyCell(row-i, col) && 
					board.cellColor(row-i, col) != color && 
					board.getCellAt(row-i, col) < 26 && 
					isKingPossibleNextMove(row-i, col)){
				board.setPawnAt(row-i, col, 26 + board.getCellAt(row-i, col));
				break;
			}
			else if(row-i == this.row && col == this.col){}
			else
				break;
	}
	
	private void horseSuggestion(int row, int col){
		
		if(col + 2 < 8){
			if(row - 1 >= 0 && (board.isEmptyCell(row-1, col+2) || 
							(!board.isEmptyCell(row-1, col+2) && 
							board.cellColor(row-1, col+2) != color &&
							board.getCellAt(row-1, col+2) < 26 && 
							isKingPossibleNextMove(row-1, col+2))))
				board.setPawnAt(row-1 , col+2, 26 + board.getCellAt(row-1, col+2));
			if(row + 1 < 8 && (board.isEmptyCell(row+1, col+2) ||
						(!board.isEmptyCell(row+1, col+2) && 
							board.cellColor(row+1, col+2) != color &&
							board.getCellAt(row+1, col+2) < 26 && 
							isKingPossibleNextMove(row+1, col+2))))
				board.setPawnAt(row+1 , col+2, 26 + board.getCellAt(row+1, col+2));
		}

		if(col - 2 >= 0){
			if(row - 1 >= 0 && (board.isEmptyCell(row-1, col-2) ||
					(!board.isEmptyCell(row-1, col-2) && 
							board.cellColor(row-1, col-2) != color &&
							board.getCellAt(row-1, col-2) < 26 && 
							isKingPossibleNextMove(row-1, col-2))))
				board.setPawnAt(row-1 , col-2, 26 + board.getCellAt(row-1, col-2));
			if(row + 1 < 8 && (board.isEmptyCell(row+1, col-2) ||
					(!board.isEmptyCell(row+1, col-2) && 
							board.cellColor(row+1, col-2) != color &&
							board.getCellAt(row+1, col-2) < 26 && 
							isKingPossibleNextMove(row+1, col-2))))
				board.setPawnAt(row+1 , col-2, 26 + board.getCellAt(row+1, col-2));
		}
		
		if( row + 2 < 8 ){
			if( col + 1 < 8 && (board.isEmptyCell(row+2, col+1) ||
					(!board.isEmptyCell(row+2, col+1) && 
							board.cellColor(row+2, col+1) != color &&
							board.getCellAt(row+2, col+1) < 26 && 
							isKingPossibleNextMove(row+2, col+1))))
				board.setPawnAt(row+2,col+1, 26 + board.getCellAt(row+2, col+1));
			if( col - 1 >= 0 && (board.isEmptyCell(row+2, col-1) ||
					(!board.isEmptyCell(row+2, col-1) && 
							board.cellColor(row+2, col-1) != color &&
							board.getCellAt(row+2, col-1) < 26 && 
							isKingPossibleNextMove(row+2, col-1))))
				board.setPawnAt(row+2,col-1, 26 + board.getCellAt(row+2, col-1));
		}
		
		if( row - 2 >= 0){
			if( col + 1 < 8 && (board.isEmptyCell(row-2, col+1) ||
					(!board.isEmptyCell(row-2, col+1) && 
							board.cellColor(row-2, col+1) != color &&
							board.getCellAt(row-2, col+1) < 26 && 
							isKingPossibleNextMove(row-2, col+1))))
				board.setPawnAt(row-2,col+1, 26 + board.getCellAt(row-2, col+1));
			if( col - 1 >= 0 && (board.isEmptyCell(row-2, col-1) ||
					(!board.isEmptyCell(row-2, col-1) && 
							board.cellColor(row-2, col-1) != color &&
							board.getCellAt(row-2, col-1) < 26 && 
							isKingPossibleNextMove(row-2, col-1))))
				board.setPawnAt(row-2,col-1, 26 + board.getCellAt(row-2, col-1));
		}
	}
	
	private void bishopSuggestion(int row, int col){
		
		for(int i = 1 ; row + i < 8 && col - i >= 0 ; i++)
			if(board.isEmptyCell(row+i, col-i) || board.isOrangeCell(row+i, col-i))
				board.setPawnAt(row+i, col-i, 26);
			else if(!board.isEmptyCell(row+i, col-i) && 
					board.cellColor(row+i, col-i) != color && 
					board.getCellAt(row+i, col-i) < 26 && 
					isKingPossibleNextMove(row+i, col-i)){
				board.setPawnAt(row+i, col-i, 26 + board.getCellAt(row+i, col-i));
				break;
			}
			else if(row+i == this.row && col-i == this.col){}
			else
				break;
		
		for(int i = 1 ; row - i >=  0 && col - i >= 0 ; i++)
			if(board.isEmptyCell(row-i, col-i) || board.isOrangeCell(row-i, col-i))
				board.setPawnAt(row-i, col-i,26);
			else if(!board.isEmptyCell(row-i, col-i) && 
					board.cellColor(row-i, col-i) != color && 
					board.getCellAt(row-i, col-i) < 26 && 
					isKingPossibleNextMove(row-i, col-i)){
				board.setPawnAt(row-i, col-i, 26 + board.getCellAt(row-i, col-i));
				break;
			}
			else if(row-i == this.row && col-i == this.col){}
			else
				break;
	
		for(int i = 1 ; row - i >= 0 && col + i < 8 ; i++)
			if(board.isEmptyCell(row-i, col+i) || board.isOrangeCell(row-i, col+i))
				board.setPawnAt(row-i, col+i, 26);
			else if(!board.isEmptyCell(row-i, col+i) && 
					board.cellColor(row-i, col+i) != color && 
					board.getCellAt(row-i, col+i) < 26 && 
					isKingPossibleNextMove(row-i, col+i)){
				board.setPawnAt(row-i, col+i, 26 + board.getCellAt(row-i, col+i));
				break;
			}
			else if(row-i == this.row && col+i == this.col){}
			else
				break;
		
		for(int i = 1 ; row + i < 8 && col + i < 8 ; i++)
			if(board.isEmptyCell(row+i, col+i) || board.isOrangeCell(row+i, col+i))
				board.setPawnAt(row+i, col+i, 26);
			else if(!board.isEmptyCell(row+i, col+i) && 
					board.cellColor(row+i, col+i) != color && 
					board.getCellAt(row+i, col+i) < 26 && 
					isKingPossibleNextMove(row+i, col+i)){
				board.setPawnAt(row+i, col+i, 26 + board.getCellAt(row+i, col+i));
				break;
			}
			else if(row+i == this.row && col+i == this.col){}
			else
				break;
	}
	
	private void queenSuggestion(int row, int col){
		rookSuggestion(row,col);
		bishopSuggestion(row,col);
	}	

	private void kingSuggestion(int row, int col){

		if(col - 1 >= 0 && (board.isEmptyCell(row, col-1) ||
				(board.cellColor(row, col-1) != color && 
				board.getCellAt(row, col-1) < 26 && 
				isKingPossibleNextMove(row, col-1))))
			board.setPawnAt(row, col-1, 26 + board.getCellAt(row, col-1));
		
		if(col - 1 >= 0 && row + 1 < 8 && (board.isEmptyCell(row+1, col-1) ||
				(board.cellColor(row+1, col-1) != color && 
				board.getCellAt(row+1, col-1) < 26 && 
				isKingPossibleNextMove(row+1, col-1))))
			board.setPawnAt(row+1, col-1, 26 + board.getCellAt(row+1, col-1));
		
		if(col - 1 >= 0 && row - 1 >= 0 && (board.isEmptyCell(row-1, col-1) ||
				(board.cellColor(row-1, col-1) != color && 
				board.getCellAt(row-1, col-1) < 26 && 
				isKingPossibleNextMove(row-1, col-1))))
			board.setPawnAt(row-1, col-1, 26 + board.getCellAt(row-1, col-1));
		
		if(row - 1 >= 0 && (board.isEmptyCell(row-1, col) ||
				(board.cellColor(row-1, col) != color && 
				board.getCellAt(row-1, col) < 26 && 
				isKingPossibleNextMove(row-1, col))))
			board.setPawnAt(row-1, col, 26 + board.getCellAt(row-1, col));
		
		if(row - 1 >= 0 && col + 1 < 8 && (board.isEmptyCell(row-1, col+1) ||
				(board.cellColor(row-1, col+1) != color && 
				board.getCellAt(row-1, col+1) < 26 && 
				isKingPossibleNextMove(row-1, col+1))))
			board.setPawnAt(row-1, col+1, 26 + board.getCellAt(row-1, col+1));
		
		if(col + 1 < 8 && (board.isEmptyCell(row, col+1) ||
				(board.cellColor(row, col+1) != color && 
				board.getCellAt(row, col+1) < 26 && 
				isKingPossibleNextMove(row, col+1))))
			board.setPawnAt(row, col+1, 26 + board.getCellAt(row, col+1));
		
		if(row + 1 < 8 && col + 1 < 8 && (board.isEmptyCell(row+1, col+1) ||
				(board.cellColor(row+1, col+1) != color && 
				board.getCellAt(row+1, col+1) < 26 && 
				isKingPossibleNextMove(row+1, col+1))))
			board.setPawnAt(row+1, col+1, 26 + board.getCellAt(row+1, col+1));
		
		if(row + 1 < 8 && (board.isEmptyCell(row+1, col) ||
				(board.cellColor(row+1, col) != color && 
				board.getCellAt(row+1, col) < 26 && 
				isKingPossibleNextMove(row+1, col))))
			board.setPawnAt(row+1, col, 26 + board.getCellAt(row+1, col));
	}
	
	//il re imposta la sua prossima posizione
	private boolean setKingSuggestion(){
		int counter = 0;
	
		if(col - 1 >= 0 && board.getCellAt(row, col-1) < 26 && (board.isEmptyCell(row, col-1) || board.cellColor(row, col-1) != color)){
			putSuggestion(row, col-1);
			counter++;
		}
		
		if(col - 1 >= 0 && row + 1 < 8 && board.getCellAt(row+1, col-1) < 26 && (board.isEmptyCell(row+1, col-1) || board.cellColor(row+1, col-1) != color)){
			putSuggestion(row+1, col-1);
			counter++;
		}
		
		if(col - 1 >= 0 && row - 1 >= 0 && board.getCellAt(row-1, col-1) < 26 && (board.isEmptyCell(row-1, col-1) || board.cellColor(row-1, col-1) != color)){
			putSuggestion(row-1, col-1);
			counter++;
		}
		
		if(row - 1 >= 0 && board.getCellAt(row-1, col) < 26 && (board.isEmptyCell(row-1, col) || board.cellColor(row-1, col) != color)){
			putSuggestion(row-1, col);
			counter++;
		}
		
		if(row - 1 >= 0 && col + 1 < 8 && board.getCellAt(row-1, col+1) < 26 && (board.isEmptyCell(row-1, col+1) || board.cellColor(row-1, col+1) != color)){
			putSuggestion(row-1, col+1);
			counter++;
		}
		
		if(col + 1 < 8 && board.getCellAt(row, col+1) < 26 && (board.isEmptyCell(row, col+1) || board.cellColor(row, col+1) != color)){
			putSuggestion(row, col+1);
			counter++;
		}
		
		if(row + 1 < 8 && col + 1 < 8 && board.getCellAt(row+1, col+1) < 26 && (board.isEmptyCell(row+1, col+1) || board.cellColor(row+1, col+1) != color)){
			putSuggestion(row+1, col+1);
			counter++;
		}
		
		if(row + 1 < 8 && board.getCellAt(row+1, col) < 26 && (board.isEmptyCell(row+1, col) || board.cellColor(row+1, col) != color)){
			putSuggestion(row+1, col);
			counter++;
		}
		
		return counter != 0;
	}
	
	//verifica se il re può andare in quella cella
	public boolean isKingPossibleNextMove(int row, int col){
		return ((row-this.row) == -1 && (col-this.col) == 0) ||
				((row-this.row) == -1 && (col-this.col) == 1) ||
				((row-this.row) == 0 && (col-this.col) == 1) ||
				((row-this.row) == 1 && (col-this.col) == 1) ||
				((row-this.row) == 1 && (col-this.col) == 0) ||
				((row-this.row) == 1 && (col-this.col) == -1) ||
				((row-this.row) == 0 && (col-this.col) == -1) ||
				((row-this.row) == -1 && (col-this.col) == -1);
	}
}	