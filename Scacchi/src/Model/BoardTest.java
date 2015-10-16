package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	Board b = new Board();
	
	@Test
	public void testIsEmptyCell() {				//controllo se i spazi vuoti iniziali sono giusti
		for(int row=2;row<=5;row++)
			for(int col=0;col<=7;col++)
				assertTrue(b.isEmptyCell(row, col));
	}

	@Test
	public void testIsWhiteCell() {				//controllo se ci sono tutte le pedine bianche
		for(int row=6;row<=7;row++)
			for(int col=0;col<=7;col++)
				assertTrue(b.isWhiteCell(row, col));
	}

	@Test
	public void testIsBlackCell() {				//controllo se ci sono tutte le pedine nere
		for(int row=0;row<=1;row++)
			for(int col=0;col<=7;col++)
				assertTrue(b.isBlackCell(row, col));
	}

	@Test
	public void testIsBlackPawnAt() {			//controllo se ci sono tutte i pedoni neri nella posizione giusta
			for(int col=0;col<=7;col++)
				assertEquals(1,b.getCellAt(1, col));
	}
	
	@Test
	public void testIsWhitePawnAt() {			//controllo se ci sono tutte i pedoni bianchi nella posizione giusta
			for(int col=0;col<=7;col++)
				assertEquals(7,b.getCellAt(6, col));
	}
	
	@Test
	public void testBlackRookAt() {         		  //controllo se la torre nera è nella posizione iniziale giusta
		assertEquals(2,b.getCellAt(0, 0));
		assertEquals(2,b.getCellAt(0, 7));

	}
	
	@Test
	public void testWhiteRookAt() {          		 //controllo se la torre bianca è nella posizione iniziale giusta
		assertEquals(8,b.getCellAt(7, 0));
		assertEquals(8,b.getCellAt(7, 7));

	}
	
	@Test
	public void testBlackHorseAt() {         		  //controllo se il cavallo nero è nella posizione iniziale giusta
		assertEquals(3,b.getCellAt(0, 1));
		assertEquals(3,b.getCellAt(0, 6));

	}
	
	@Test
	public void testWhiteHorseAt() {         		  //controllo se il cavallo bianco è nella posizione iniziale giusta
		assertEquals(9,b.getCellAt(7, 1));
		assertEquals(9,b.getCellAt(7, 6));

	}
	
	@Test
	public void testBlackBishopAt() {  			//controllo se l'alfiere nero è nella posizione iniziale giusta
				assertEquals(4,b.getCellAt(0, 2));
				assertEquals(4,b.getCellAt(0, 5));
	}
	
	@Test
	public void testWhiteBishopAt() {  			//controllo se l'alfiere bianco è nella posizione iniziale giusta
				assertEquals(10,b.getCellAt(7, 2));
				assertEquals(10,b.getCellAt(7, 5));
	}
		
	@Test
	public void testBlackQueenAt() {           //controllo se la regina nera è nella posizione iniziale giusta
		assertEquals(5,b.getCellAt(0, 3));
	
	}
	
	@Test
	public void testWhiteQueenAt() {           //controllo se la regina bianca è nella posizione iniziale giusta
		assertEquals(11,b.getCellAt(7, 3));
	
	}
	
	@Test
	public void testBlackKingAt() {           //controllo se il re nero è nella posizione iniziale giusta
		assertEquals(6,b.getCellAt(0, 4));
	
	}
	@Test
	public void testWhiteKingAt() {           //controllo se il re bianco è nella posizione iniziale giusta
		assertEquals(12,b.getCellAt(7, 4));
	
	}
}
