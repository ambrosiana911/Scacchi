package Model;

public class Board {
	private int[][] cells;
	private final int row = 8;
	private final int col = 8;

	private final static int emptyCell = 0;
	private final static int greenCell = 13;
	private final static int orangeCell = 26;
	
	private final static int blackPawn = 1;
	private final static int blackRook = 2;
	private final static int blackHorse = 3;
	private final static int blackBishop = 4;
	private final static int blackQueen = 5;
	private final static int blackKing = 6;
	
	private final static int whitePawn = 7;
	private final static int whiteRook = 8;
	private final static int whiteHorse = 9;
	private final static int whiteBishop = 10;
	private final static int whiteQueen = 11;
	private final static int whiteKing = 12;

	
	private int pawnToMove,rowFrom,colFrom;
	public int whiteUnderCheck = 0;
	public int blackUnderCheck = 0;

	public Board(){
		buildBoard();
	}

	// creiamo la scacchiera 
	private void buildBoard(){
		cells = new int[row][col];

		for (int x = 0; x < 8; x++){
			for (int y = 0; y < 8; y++){
				setPawnAt(x, y, emptyCell );
			}
		}
		
		//sistemo i pedoni neri
		for (int counter = 0; counter < 8; counter++)
			setPawnAt(1,counter, blackPawn );
		//sistemo i pedoni bianchi
		for (int counter = 0; counter < 8; counter++)
			setPawnAt(6,counter, whitePawn );
		
		setPawnAt(0,1,blackHorse);
		setPawnAt(0,6,blackHorse);
		setPawnAt(0,0,blackRook);
		setPawnAt(0,7,blackRook);
		setPawnAt(0,2,blackBishop);
		setPawnAt(0,5,blackBishop);
		setPawnAt(0,4,blackKing);
		setPawnAt(0,3,blackQueen);
		
		setPawnAt(7,1,whiteHorse);
		setPawnAt(7,6,whiteHorse);
		setPawnAt(7,0,whiteRook);
		setPawnAt(7,7,whiteRook);
		setPawnAt(7,2,whiteBishop);
		setPawnAt(7,5,whiteBishop);
		setPawnAt(7,3,whiteQueen);
		setPawnAt(7,4,whiteKing);
		
	}

	// ritorna il contenuto della cella a x ,y
	public int getCellAt(int row, int col) {
		return cells[row][col];
	}

	// mette una pedina a x,y
	public void setPawnAt(int row, int col, int pedina) {
		cells[row][col] = pedina;
	}
	
	//ritorna il colore della cella
	public boolean cellColor(int row, int col){
		return isWhiteCell(row,col);
	}
	
	//ritorna true se la cella è nera
	public boolean isBlackCell(int row, int col){
		return cells[row][col] >= 1 && cells[row][col] <= 6;
	}
	
	//ritorna true se la cella è bianca
	public boolean isWhiteCell(int row, int col){
		return cells[row][col] >= 7 && cells[row][col] <= 12;
	}
	
	//ritorna true se la cella è verde
	public boolean isGreenCell(int row, int col){
		return cells[row][col] == greenCell;
	}
	
	//ritorna true se la cella è vuota
	public boolean isEmptyCell(int row, int col){
		return cells[row][col] == emptyCell;
	}
	
	//ritorna true se la cella è arancione
	public boolean isOrangeCell(int row, int col){
		return cells[row][col] == orangeCell;
	}
	
	//memorizza la pedina da spostare
	public void setPawnToMove(int pedina, int row, int col){
		pawnToMove = pedina;
		rowFrom = row;
		colFrom = col;
	}
	
	//ritorna la pedina da spostare
	public int getPawnToMove(){
		return pawnToMove;
	}
	
	//ritorna la riga della pedina da spostare
	public int getRowFrom(){
		return rowFrom;
	}
	
	//ritorna la colonna della pedina da spostare
	public int getColFrom(){
		return colFrom;
	}
	
	//ritorna la riga del re bianco
	public int rowWhiteKing(){
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++)
				if(getCellAt(row, col) % 13 == 12)
					return row;
		return 0;
	}
		
	//ritorna la colonna del re bianco
	public int colWhiteKing(){
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++)
				if(getCellAt(row, col) % 13 == 12)
					return col;
		return 0;
	}
		
	//ritorna la riga del re nero
	public int rowBlackKing(){
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++)
				if(getCellAt(row, col) % 13 == 6)
					return row;
		return 0;
	}
	
	//ritorna la colonna del re nero
	public int colBlackKing(){
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++)
				if(getCellAt(row, col) % 13 == 6)
					return col;
		return 0;
	}
	
}