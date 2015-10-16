package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Board;

public class winner extends JFrame{
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public winner(boolean color){
		String black = "CHECKMATE!!!  The winner is : Player 2"; 
		String white = "CHECKMATE!!!  The winner is : Player 1"; 
		
		setLayout(new BorderLayout());
		setLocation( (dim.width - 400) / 2, (dim.height - 400) / 2 );
		JLabel scaccoMatto = new JLabel(color? white : black);
		JButton nuovoGioco = new JButton("Nuovo Gioco");
		JButton esciButton = new JButton("Esci");
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(nuovoGioco);
		panel.add(esciButton);

		this.add(scaccoMatto, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.setSize(400, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		nuovoGioco.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {			
				//prendo tutti i frame aperti; se sono istanza di Game li chiudo
				Frame[] frames = getFrames();	
				for(Frame frame : frames){
					if(frame instanceof Game)
						frame.setVisible(false);
				}
				new Game(new Board()).setVisible(true);
				dispose();
			}
		});
		
		esciButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
