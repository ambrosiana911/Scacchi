package Controller;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Board;

public class SuggestionTest {
	
	Board b = new Board();
	
	@Test
	public void testChooseSuggestionWhitePawn() {		//movimento iniziale in avanti pedone bianco
		Suggestion s = new Suggestion(b, 6,4); 
		assertTrue(b.isGreenCell(5, 4));	
	}
	
	@Test
	public void testChooseSuggestionBlackPawn() {		//movimento iniziale in avanti pedone nero
		Suggestion s = new Suggestion(b, 1,5); 
		assertTrue(b.isGreenCell(2, 5));	
	}
	
	@Test
	public void testChooseSuggestionWhiteHorse() {		//movimento iniziale a L cavallo bianco
		Suggestion s = new Suggestion(b, 7,6); 
		assertTrue(b.isGreenCell(5, 7));	
	}
	
	@Test
	public void testChooseSuggestionBlackHorse() {		//movimento iniziale a L cavallo nero
		Suggestion s = new Suggestion(b, 0,1); 
		assertTrue(b.isGreenCell(2, 0));	
	}
	
	@Test
	public void testChooseSuggestionWhiteRookOrizontally() {		//movimento torre bianca in orrizontale
		//...
		b.setPawnAt(5, 7, 8);
		Suggestion s = new Suggestion(b, 5,7); 
		assertTrue(b.isGreenCell(5, 2));	
	}
	
	@Test
	public void testChooseSuggestionBlackRookVertically() {		//movimento torre bianca in verticale
		//...
		b.setPawnAt(3, 0, 2);
		Suggestion s = new Suggestion(b, 3,0); 
		assertTrue(b.isGreenCell(5, 0));	
	}
	
	@Test
	public void testChooseSuggestionWhiteBishop() {			//movimento alfiere bianco diagonale
		//...
		b.setPawnAt(5, 3, 10);
		Suggestion s = new Suggestion(b, 5,3); 
		assertTrue(b.isGreenCell(2, 0));	
	}
	
	@Test
	public void testChooseSuggestionWhiteQueenVertically() {		//movimento regina bianca in verticale
		//...
		b.setPawnAt(5, 2, 11);
		Suggestion s = new Suggestion(b, 5,2); 
		assertTrue(b.isGreenCell(5, 7));	
	}
	
	@Test
	public void testChooseSuggestionWhiteQueenOrizontally() {		//movimento regina bianca in orizzontale
		//...
		b.setPawnAt(5, 2, 11);
		Suggestion s = new Suggestion(b, 5,2); 
		assertTrue(b.isGreenCell(3, 2));	
	}
	
	@Test
	public void testChooseSuggestionWhiteQueenDiagonally() {		//movimento regina bianca in diagonale
		//...
		b.setPawnAt(5, 2, 11);
		Suggestion s = new Suggestion(b, 5,2); 
		assertTrue(b.isGreenCell(4, 3));	
	}
	
	@Test
	public void testChooseSuggestionWhiteKing() {		//movimento re bianco
		//...
		b.setPawnAt(4, 5, 12);
		Suggestion s = new Suggestion(b, 4,5); 
		assertTrue(b.isGreenCell(5, 5));	
	}
	
	@Test
	public void testChooseSuggestionWhiteKingVertically() {		//movimento re bianco in verticale
		//...
		b.setPawnAt(4, 5, 12);
		Suggestion s = new Suggestion(b, 4,5); 
		assertTrue(b.isGreenCell(5, 5));	
	}
	
	@Test
	public void testChooseSuggestionWhiteKingOrizontally() {		//movimento re bianco in orizzontale
		//...
		b.setPawnAt(4, 5, 12);
		Suggestion s = new Suggestion(b, 4,5); 
		assertTrue(b.isGreenCell(5, 6));	
	}
	
	@Test
	public void testChooseSuggestionWhiteKingDiagonally() {		//movimento re bianco in diagonale
		//...
		b.setPawnAt(4, 5, 12);
		Suggestion s = new Suggestion(b, 4,5); 
		assertTrue(b.isGreenCell(3,4));	
	}
	
}
