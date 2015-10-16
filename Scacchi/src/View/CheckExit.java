package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckExit extends JFrame {
	 
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public CheckExit() {
		super("Are you sure?");
		setLayout(new BorderLayout());
		setLocation( (dim.width - 400) / 2, (dim.height - 400) / 2 );
		add(new JLabel("Sei sicuro di volere uscire?"), BorderLayout.NORTH);
	 
			
		JPanel siNo = new JPanel();
		siNo.setLayout(new FlowLayout());

		JButton yes = new JButton("Si");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});

		siNo.add(yes);
		siNo.add(new JButton("No") {{
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
				
			});
		}}
		);

		this.add(siNo, BorderLayout.CENTER);
		this.setSize(400, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}