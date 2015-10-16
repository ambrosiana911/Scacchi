package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.accessibility.Accessible;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.MenuElement;
import javax.swing.text.StyledEditorKit.ItalicAction;

public class Elements extends JPanel{
	private final static ImageIcon pedinaNera = new ImageIcon("src/Images/bp.png");
	private final static ImageIcon pedinaBianca = new ImageIcon("src/Images/wp.png");
	private static JPanel panelText = new JPanel();	
	private JButton newGameButton = new JButton("New Game");    
	private JButton resignButton = new JButton("Resign Game"); 

	//
	public JPanel createTitle(int turn){
		JLabel player1 = new JLabel("Player1");
		player1.setFont(new Font("", Font.BOLD, 15));
		player1.setForeground(Color.YELLOW);
		
		JLabel player2 = new JLabel("Player2");
		player2.setFont(new Font("", Font.BOLD, 15));
		player2.setForeground(Color.YELLOW);
		
		panelText.setBackground(new Color(0,110,0));
		
		panelText.add(addIcon(pedinaBianca));
		panelText.add(turn == 0 ? player1 : addText("Player 1")) ;
		panelText.add(addText("vs"));
		panelText.add(turn == 1 ? player2 : addText("Player 2"));
		panelText.add(addIcon(pedinaNera));

		return panelText;
	}

	// aggiungiamo il text
	private JLabel addText(String string){
		JLabel text = new JLabel(string);
		text.setForeground(Color.WHITE);

		return text;	
	}

	// aggiungiamo l'icona
	private JLabel addIcon(ImageIcon icon){
		JLabel img = new JLabel();

		img.setIcon(icon);	

		return img;	

	}
	
	//pulisce il JPanel
	public static void destroyPanel(){
		panelText.removeAll();
	}
}