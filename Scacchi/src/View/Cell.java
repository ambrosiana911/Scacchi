package View;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Cell extends JButton{
	private final static ImageIcon pedinaNera = new ImageIcon("src/Images/bp.png"); 
	private final static ImageIcon cavalloNero = new ImageIcon("src/Images/bn.png");
	private final static ImageIcon torreNera = new ImageIcon("src/Images/br.png");
	private final static ImageIcon alfiereNero = new ImageIcon("src/Images/bb.png");
	private final static ImageIcon reNero = new ImageIcon("src/Images/bq.png");
	private final static ImageIcon reginaNera = new ImageIcon("src/Images/bk.png");
	
	
	private final static ImageIcon pedinaBianca = new ImageIcon("src/Images/wp.png");
	private final static ImageIcon cavalloBianco = new ImageIcon("src/Images/wn.png");
	private final static ImageIcon torreBianca = new ImageIcon("src/Images/wr.png");
	private final static ImageIcon alfiereBianco = new ImageIcon("src/Images/wb.png");	
	private final static ImageIcon reginaBianca = new ImageIcon("src/Images/wq.png");
	private final static ImageIcon reBianco = new ImageIcon("src/Images/wk.png");

	// costruttore
	public Cell(Color c, int i){
		
		setCell(c);
		setPedina(i);
	}

	// impostiamo le pedine
	public void setPedina(int i){

		switch (i){
		case 0:
			break;
		case 1:
			this.setIcon((Icon) pedinaNera);
			break;
		case 2:
			this.setIcon(torreNera);
			break;
		case 3:
			this.setIcon(cavalloNero);
			break;
		case 4:
			this.setIcon(alfiereNero);
			break;
		case 5:
			this.setIcon(reNero);
			break;
		case 6:
			this.setIcon(reginaNera);
			break;
		case 7:
			this.setIcon(pedinaBianca);
			break;
		case 8:
			this.setIcon(torreBianca);
			break;
		case 9:
			this.setIcon(cavalloBianco);
			break;
		case 10:
			this.setIcon(alfiereBianco);
			break;
		case 11:
			this.setIcon(reginaBianca);
			break;
		case 12:
			this.setIcon(reBianco);
			break;
		default:
			if(i > 39){
				setCell(Color.ORANGE);
				this.setPedina(i%39);
			}
			else if(i >= 26){
				setCell(Color.RED);
				this.setPedina(i%26);
			}
			else{
				setCell(Color.GREEN);
				this.setPedina(i%13);
			}
			break;
		}
	}

	// impostiamo il colore della cella
	public void setCell(Color color){
		this.setBackground(color);
	}	
}